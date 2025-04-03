/****************  giao diện trang edit **********************/
'use client';
import { useEffect, useState } from 'react';

import Image from 'next/image';

//import interface  types định kiểu dữ liệu cho dữ liệu cho page props edit product
import {EditProductPropsTypes, ModalContextType, ToastContextType, CategoryTypes,
  SupplierTypes, ToastContextType } from "@/types/TsSetup";

//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';
//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import lib fontAwesome cho NextJs
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faArrowLeft, faSave, faPenToSquare} from '@fortawesome/free-solid-svg-icons'

//import lib axios xử lý call api co mục select category và supplier id
import axios from '@/lib/axios';

//make variale api url file upload img
const API_UPLOAD_URL = "http://localhost:8080/uploads/";

const UpdateModal: React.FC<EditProductPropsTypes> = ({id, onReload}) => {

  //khai bao bien trang thai state - state dùng cho input nhập liệu từ client
  const [listCategory, setListCategory] = useState<CategoryTypes[]>([]);
  const [listSupplier, setListSupplier] = useState<SupplierTypes[]>([]);
  const [productCode, setProductCode] = useState<string | null>(null);
  const [productName, setProductName] = useState<string | null>(null);
  const [shortDescription, setShortDescription] = useState<string | null>(null);
  const [description, setDescription] = useState<string | null>(null);
  const [standardCost, setStandardCost] = useState<number | null>(null);
  const [listPrice, setListPrice] = useState<number | null>(null);
  const [quantityPerUnit, setQuantityPerUnit] = useState<number | null>(null);
  const [discontinued, setDiscontinued] = useState<boolean | null>(null);
  const [isFeatured, setIsFeatured] = useState<boolean | null>(null);
  const [isNew, setIsNew] = useState<boolean | null>(null);
  const [categoryId, setCategoryId] = useState<number | null>(null);
  const [supplierId, setSupplierId] = useState<number | null>(null);


  //khai báo state tu useToast trong ToastContext truyền vào bien state
  const {showToast} = useToast() as ToastContextType;
  //state trang thai dung voi useModal cua ModalContext:
  const {closeModal} = useModal() as ModalContextType;


  /***method: xử lý sự kiện khi choosefile img thì ảnh hiện lên để có thể xem trc đc ảnh vừa chọn**/
  const [imagePreview, setImagePreview] = useState(null); // ảnh xem trc khi choose file new img
  const [existingImage, setExistingImage] = useState(null); // Ảnh có sẵn từ database hiện lên khi mở form edit
  const[file, setFile] = useState(null); //state chỉ lưu file img  khi create sản phẩm

  /**method: xử lý xem trc img **/
  const handleImgPreview = (e: React.ChangeEvent<HTMLInputElement>) => {
    //đọc nd tập tin cần hiển thị
    const file = e.target.files?.[0]
    if(file){
      //tạo Url láy địa chứa img xem trước
      const preView = URL.createObjectURL(file)
      //update url vào setImaePreview để cập nhặt
      setImagePreview(preView) 

      //lưu file ghi nhận img vào create sản phẩm
      setFile(file)
    }
  }




  /***hàm xử lý sự kiện submit nut edit khi nhán lưu file: create product*****/
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
      //ngăn sk mặc đinh khi nhấn nút submit của trình duyệt
      e.preventDefault()
      // tạo hàm gọi api thêm mới sản phẩm 
      const updateFormProduct = async ()=>{
        try{
          /*tạo formData để gửi dữ liệu lên server -> trong js khi gửi dl thi phải 
          chuyển sang dạng formData: nó có hai mục file(chứa imge và data là các 
          value còn lại xem bên postman sẽ rõ chỗ create products ấy*/
          const formData = new FormData();
         
           // Nếu có file mới thì gửi, không thì giữ ảnh cũ
           formData.append("file", file);
          
          //tạo biến lưu trữ value 
          const newObjProducts = {
              productCode: productCode,
              productName: productName,
              shortDescription: shortDescription,
              description: description,
              standardCost: standardCost,
              listPrice: listPrice ,
              quantityPerUnit: quantityPerUnit,
              discontinued: discontinued,
              featured: isFeatured,
              new: isNew,
              categoryId: categoryId,
              supplierId: supplierId
          }
          // JSON.stringify(newObjProducts): chuyển đổi obj sang json string
          formData.append('data', JSON.stringify(newObjProducts));

          /*nhờ lib axios gửi dữ liệu lên server (lưu ý có kèm theo file imge)
           => lưu ý: gửi formData value từ form nhập liệu lên csdl thực hiện create 
           thoogn qua api thì cần gửi dạng multipart
          */
           const response = await axios.put(`shop-products/update/${id}`, 
            formData,
             {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
             }
           )

           //goi showToast vao de su dung hien thi TOast trong useEffect
           showToast(response.data.msg, 'success')
           
            /**reload lại trang sang creat
            *-> ở mục nay sau create nếu có phân trang thi nó reload lại trang
            và đẩy dòng recore mới tạo vào page phân trang cúi cùng tránh
            cứ đồn ở page 1 
            -> cách làm tao tham số onReload khai báo ở method đầu const 
            CreateModal = ({listProduct, setListProduct, onReload}) 
            -> qua bên page tổng của Products truyenf đối só là method handleReload vào 
            tham số onReload nay và xử lý reload bền page cảu product tổng
            *  **/
            //nếu có onReload thì gọi hàm onReload
            if (onReload) {
              onReload();
            }  
           
            
          //close modal khi xóa thành công -xóa xong đóng hộp thoại xóa di
          closeModal()    

        }catch(error){
          const errorMessage = error.response?.data?.message || 'Có lỗi khi update sản phẩm!';
          showToast(errorMessage, 'danger');
        }
      }


      //gọi thực thi hàm xử lý createFormProducts
      await updateFormProduct()
    }



  

/**********su dung useEffect để thực thi call api cho mục category và supplier id **********/
//su dung useEffect de chay duy nhat lan dau khi compoents nay render xong
 useEffect(()=>{
   //goi api lay danh sach category
   const fetchCategory = async () => {
      try{
         const res = await axios.get("shop-category")
         setListCategory(res.data.data);
      }catch(error){
          console.log('co loi khi tai du lieu', error)
      }
   }

    //goi api lay danh sach suppliers
    const fetchSuppliers = async () => {
      try{
         const res = await axios.get("shop-suppliers")
         setListSupplier(res.data.data);
      }catch(error){
          console.log('co loi khi tai du lieu', error)
      }
   }

   fetchCategory()
   fetchSuppliers()
 }, [])


 /*****sử dụng thêm useEffect de update tiêm các fill value có sẵn 
  * trong record đã đc tạo từ csdl tiêm vào các input của form edit
  * (nghĩa là khi mở form edit của id muon update thi các value trc 
  * đó đã tạo tự điền vào các input.. liên quan)*******/
  useEffect(() => {
    if (!id) return;

    const fetchProductById = async () => {
      try {
        const res = await axios.get(`shop-products/${id}`);
        const data = res.data.data;
        if (data) {

        //Đoạn code này gán dữ liệu sản phẩm từ API vào các state tương ứng để hiển thị trên giao diện.
        /*Ở đây, ?? (nullish coalescing) đảm bảo nếu state là null hoặc undefined, 
        nó sẽ lấy giá trị mặc định thay vì gây lỗi.
        -> ??: nếu data.state có giá trị thì lấy giá trị đó, không thì lấy giá trị 
        mặc định '' /false/...
        */
          setProductCode(data.productCode ?? '');
          setProductName(data.productName ?? '');
          setShortDescription(data.shortDescription ?? '');
          setDescription(data.description ?? '');
          setStandardCost(data.standardCost ?? '');
          setListPrice(data.listPrice ?? '');
          setQuantityPerUnit(data.quantityPerUnit ?? '');
          setDiscontinued(data.discontinued ?? false);
          setIsFeatured(data.featured ?? false);
          setIsNew(data.new ?? false);
          setCategoryId(data.categoryId ?? '');
          setSupplierId(data.supplierId ?? '');

          // Kiểm tra ảnh từ database
          if (data.image) {
            setExistingImage(API_UPLOAD_URL + data.image);
          }
        }
      } catch (error) {
        console.error('Lỗi khi tải dữ liệu', error);
      }
    };

    fetchProductById();
  }, [id]);


 


  return(
     <>
       <h1 className="color-text-header text-center mt-4 mb-4"> Update value {id}</h1>
        <form action="" onSubmit={handleSubmit}>
            {/*******hình sản phảm *******/}
            <div className="mb-3 d-flex flex-column align-items-center">
                <label htmlFor="image" className="form-label color_text_product">Hình xem trước</label>
              
                    {/* Hiển thị ảnh preview nếu có (ảnh mới chọn) */}
                    {imagePreview ? (
                      <Image 
                        src={imagePreview} 
                        alt="Preview" 
                        className="img-thumbnail mb-2" 
                        width={350} height={300}
                        style={{ objectFit: 'cover' }} 
                      />
                    ) : existingImage ? (
                      // Nếu chưa chọn ảnh mới, hiển thị ảnh cũ từ database
                      <Image 
                        src={existingImage}  
                        alt="Current Product Image" 
                        className="img-thumbnail mb-2" 
                        width={350} height={300}
                        style={{ objectFit: 'cover' }} 
                      />
                    ) : (
                      // Nếu không có ảnh, hiển thị ảnh mặc định
                      <Image 
                        src='/assets/admin/img/noimg.jpg' 
                        alt="No Image Available" 
                        className="img-thumbnail mb-2" 
                        width={350} height={300}
                        style={{ objectFit: 'cover' }} 
                      />
                    )}

                 {/* gắn sư kiện onChange handleImgPreview vào mục chooseimg   */}
                <input className="form-control" type="file" id="image" onChange={handleImgPreview}/>
            </div>


            {/* mã sản phẩm */}
            <div className="mb-3">
              <label htmlFor="product_code" className="form-label color_text_product">Mã sản phẩm</label>
              
              {/* thêm onChange với state setProductCode ghi nhan giá  trị nhập từ input de lay giá trị */}
              {/* them value='' chen state bien trang thai ghi nhan value  da create trc do tiem 
              vao input de edit */}
              <input type="text" className="form-control" id="product_code" 
              placeholder="Mời nhập mã sản phẩm" 
              value={productCode ?? ''} // Nếu productCode là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
              onChange={(e)=>{setProductCode(e.target.value)}}/>
            </div>

            {/* tên sản phẩm */}
            {/* them value='' chen state bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
            <div className="mb-3">
              <label htmlFor="product_name" className="form-label color_text_product">Tên sản phẩm</label>
              <input type="text" className="form-control" id="product_name" 
              placeholder="Mời nhập tên sản phẩm"
              value={productName ?? ''} // Nếu productName là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
              onChange={(e)=>{setProductName(e.target.value)}} />
            </div>

            {/* mô tả ngắn */}
            {/* them value='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
            <div className="mb-3">
              <label htmlFor="short_description" className="form-label color_text_product">Nội dung sort</label>
              <textarea className="form-control" id="short_description" rows={2} 
              placeholder='vui lòng nhập nôi dụng mô tả ngắn ở đây' 
              value={shortDescription ?? ''} // Nếu shortDescription là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
              onChange={(e)=>{setShortDescription(e.target.value)}} />
            </div>

            {/* mô tả chi tiết */}
            {/* them value='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
            <div className="mb-3">
              <label htmlFor="description" className="form-label color_text_product">Nội dung chi tiết</label>
              <textarea className="form-control" id="description" rows={7} 
              placeholder='vui lòng nhập nôi dụng mô tả chi tiết ở đây'
              value={description ?? ''} // Nếu description là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
              onChange={(e)=>{setDescription(e.target.value)}}/>
            </div>



            {/***** nhập: giá nhập  - giá bán - số lượng sản phẩm.. chia cổ tỉ lệ 3 cột nằm chung hàng  *******/}
            <div className="row">
              {/* cột nhập nhập giá standard _code */}
              {/* them value='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
               <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="standard_code" className="form-label color_text_product">Giá gốc</label>
                      <input type="number" className="form-control" id="standard_code" 
                      placeholder="Mời giá sản phẩm" 
                      value={standardCost ?? ''} // Nếu standardCost là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
                      onChange={(e)=>{setStandardCost(Number(e.target.value))}}/>
                  </div>
               </div>

               {/* cột nhập giá bán list_price */}
               {/* them value='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
               <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="list_price" className="form-label color_text_product">Giá bán</label>
                      <input type="number" className="form-control" id="list_price" 
                      placeholder="Mời nhập giá bán sản phẩm" 
                      value={listPrice ?? ''} // Nếu listPrice là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
                      onChange={(e)=>{setListPrice(Number(e.target.value))}}/>
                  </div>
               </div>

                {/* cột nhập số lượng sản phẩm quantity_per_unit */}
                {/* them value='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
                <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="quantity_per_unit" className="form-label color_text_product">Số lượng</label>
                      <input type="number" className="form-control" id="quantity_per_unit"
                       placeholder="Mời số lượng sản phẩm" 
                       value={quantityPerUnit ?? ''} // Nếu quantityPerUnit là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
                      onChange={(e)=>{setQuantityPerUnit(Number(e.target.value))}}/>
                  </div>
               </div>
            </div><br/>





           {/**********cập nhạt trang  thái - cũng chia lam 3 cột trên một dòng **********/}
           <div className="row">
               {/* Switch check cột sản phẩm còn bán hay đã ngưng sủ dụng discontinued */}
              {/* them checked='' chen state bien trang thai ghi nhan value  da create trc do tiem 
            vao input de edit */}
               <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" id="discontinued" 
                      value="true" 
                      checked={discontinued ?? false} // Nếu discontinued là null hoặc undefined thì sẽ dùng giá trị false làm mặc định
                      onChange={(e)=>{setDiscontinued(e.target.checked)}}/>
                      <label className="form-check-label color_text_product" htmlFor="discontinued">Ngưng dùng?</label>
                  </div>
               </div>

                {/* Switch check làm nổi bật sản phẩm */}
                {/* them checked='' chen state bien trang thai ghi nhan value  da create trc do tiem 
                vao input de edit */}
              <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" 
                      id="is_featured" 
                      value="true"
                      checked={isFeatured ?? false} // Nếu isFeatured là null hoặc undefined thì sẽ dùng giá trị false làm mặc định
                      onChange={(e)=>{setIsFeatured(e.target.checked)}}/>
                      <label className="form-check-label color_text_product" htmlFor="is_featured">Làm nổi bật ?</label>
                  </div>
               </div>

              {/* Switch check làm mới sản phẩm */}
              {/* them checked='' chen state  bien trang thai ghi nhan value  da create trc do tiem 
              vao input de edit */}
              <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" id="is_new" 
                      value="true" 
                      checked={isNew ?? false} // Nếu isNew là null hoặc undefined thì sẽ dùng giá trị false làm mặc định
                      onChange={(e)=>{setIsNew(e.target.checked)}}/>
                      <label className="form-check-label color_text_product" htmlFor="is_new">Làm mới ?</label>
                  </div>
               </div>
           </div><br/>




           {/******** mục select chọn categories_id và supplier_id ***************/}
            <div className="row">
               {/* select chọn khóa ngoại categery_id */}
               <div className="col-6">
                  <div className="mb-3">
                      <label htmlFor="category_id" className="form-label color_text_product">Chuyên mục</label>
                      <select 
                        id="category_id" 
                        className="form-control" 
                        value={categoryId ?? ''} // Nếu categoryId là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
                        onChange={(e) => setCategoryId(Number(e.target.value))}
                      >

                      
                           {/* cach call api do du lieu vao trong select */}
                           {/* thêm selected vơi state  bien trang thai ghi nhan value  
                           da create trc do tiem vao input de edit */}
                           {listCategory.map((cat, index) => {
                              return(
                                  <option key={index} value={cat.id} 
                                  selected={cat.id == categoryId}>
                                    {cat.categoryName}
                                  </option>
                              )
                           })}
                      </select>
                  </div>
               </div>

               {/* select chọn khóa ngoại supplier_id */}
               <div className="col-6">
                  <div className="mb-3">
                      <label htmlFor="supplier_id" className="form-label color_text_product">Nhà cung cấp</label>
                      <select 
                        id='supplier_id' 
                        value={supplierId ?? ''}
                        className='form-control'  
                        onChange={(e)=>{setSupplierId(Number(e.target.value))}}
                       >

                      
                          {/* cach call api do du lieu vao trong select */}
                          {/* thêm selected vơi state bien trang thai ghi nhan value  
                           da create trc do tiem vao input de edit */}
                          {listSupplier.map((sup, index) => {
                              return(
                                  <option key={index} value={sup.id} 
                                  selected={sup.id == supplierId}>
                                    {sup.supplierName}
                                  </option>
                              )
                           })}
                      </select>
                  </div>
               </div>
            </div>

          {/* buton submit form save      */}
          <button className='btn btn-primary ms-2 float-end'>
            <FontAwesomeIcon icon={faPenToSquare} className='me-2' />
           Update
          </button>

        </form>

     </>
  )
}

export default UpdateModal;

  



