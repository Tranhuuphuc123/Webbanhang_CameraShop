/******trang thiết kế nội dung create của products pages******/
'use client';
import { useEffect, useState } from 'react';

import Image from 'next/image';

//import interface  types định kiểu dữ liệu cho dữ liệu cho page props createProduct.
import {CategoryTypes, SupplierTypes,  CreateProductPropsTypes, 
  ModalContextType, ToastContextType} from "@/types/TsSetup";

//nhóm lib hỗ trợ Toast trong báo trong lib react bootstrap - xem thêm trên toast/react Bootstrap
// import Toast from 'react-bootstrap/Toast';
// import ToastContainer from 'react-bootstrap/ToastContainer'; 

//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';
//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import lib fontAwesome cho NextJs
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faSave} from '@fortawesome/free-solid-svg-icons'

//import lib axios xử lý call api co mục select category và supplier id
import axios from '@/lib/axios';

//React.FC<CreateProductPropsTypes>: đinh kiểu dữ liệu ts cho props truyền vào component createProduct
const CreateModal: React.FC<CreateProductPropsTypes> = ({onReload}) => {

  //khai bao bien trang thai state - state dùng cho input nhập liệu từ client
  const [listCategory, setListCategory] = useState<CategoryTypes[]>([]);
  const [listSupplier, setListSupplier] = useState<SupplierTypes[]>([]);
  const [productCode, setProductCode] = useState<string | null>(null);
  const [productName, setProductName] = useState<string | null>(null);
  const [shortDescription, setShortDescription] = useState<string | null>(null);
  const [description, setDescription] = useState<string | null>(null);
  const [standardCode, setStandardCode] = useState<number | null>(null);
  const [listPrice, setListPrice] = useState<number | null>(null);
  const [quantityPerUnit, setQuantityPerUnit] = useState<number | null>(null);
  const [discontinued, setDiscontinued] = useState<boolean | null>(null);
  const [isFeatured, setIsFeatured] = useState<boolean | null>(null);
  const [isNew, setIsNew] = useState<boolean | null>(null);
  const [categoryId, setCategoryId] = useState<number | null>(null);
  const [supplierId, setSupplierId] = useState<number | null>(null);


  // khai báo state cho việc sử dụng trang thái thông báo Toast - Toast/lib react bootstrap
  // const [showToast, setShowToast] = useState(false); 
  // const [mesToast, setMesToast] = useState(null);
  // const [conTextToast, setConTextToast] = useState(null);
  
  //khai báo state tu useToast trong ToastContext truyền vào bien state
  const {showToast} = useToast() as ToastContextType;
  //state trang thai dung voi useModal cua ModalContext:
  const {closeModal} = useModal() as ModalContextType;




  /***method: xử lý sự kiện khi choosefile img thì ảnh hiện lên để có thể xem trc đc ảnh vừa chọn**/
  const [imagePreview, setImagePreview] = useState(null); // state lưu img xem trước khi create sản phẩm
  const[file, setFile] = useState(null); // state lưu file img  khi create sản phẩm

  /**method: xử lý xem trc img trc khi create **/
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




  /***hàm xử lý sự kiện submit nut save khi nhán lưu file: create product*****/
  //e: React.FormEvent<HTMLFormElement>: định kiểu dữ liệu ts cho event submit form
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
      //ngăn sk mặc đinh khi nhấn nút submit của trình duyệt
      e.preventDefault()
      // tạo hàm gọi api thêm mới sản phẩm 
      const createFormProducts = async ()=>{
        try{
          /*tạo formData để gửi dữ liệu lên server -> trong js khi gửi dl thi phải 
          chuyển sang dạng formData: nó có hai mục file(chứa imge và data là các 
          value còn lại xem bên postman sẽ rõ chỗ create products ấy*/
          const formData = new FormData();
          formData.append('file', file);

          //tạo biến lưu trữ value 
          const newObjProducts = {
              productCode: productCode,
              productName: productName,
              shortDescription: shortDescription,
              description: description,
              standardCode: standardCode,
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
           const response = await axios.post("shop-products/create", 
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
             
        }catch(error: any){
          const errorMessage = error.response?.data?.message || 'Có lỗi khi thêm sản phẩm!';
          showToast(errorMessage, 'danger');
        }
      }


      //gọi thực thi hàm xử lý createFormProducts
      await createFormProducts()
    }



  

/**********su dung useEffect để thực thi call api cho mục category và supplier id**********/
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

 

  return(
     <>
       <h1 className="color-text-header text-center mt-4 mb-4"> Thêm mới sản phẩm</h1>
        <form action="" onSubmit={handleSubmit}>
            {/*******hình sản phảm *******/}
            <div className="mb-3 d-flex flex-column align-items-center">
                <label htmlFor="image" className="form-label color_text_product">Hình xem trước</label>
                
                {/* hiện ảnh preview khi co chọn img */}
                {imagePreview && (
                    <Image 
                        //dẫn state img xử lý trên vào src của thẻ xem trc img
                        src={imagePreview} 
                        alt="Preview" 
                        className="img-thumbnail mb-2" 
                        width={350} height={300}
                        style={{ objectFit: 'cover'}}
                      />
                )}
                
                {/* khi khung priview khong có ảnh thì hiện ảnh no img (thg mới load trang á)     */}
                {!imagePreview && (
                    <Image 
                        //dẫn state img xử lý trên vào src của thẻ xem trc img
                        src='/assets/admin/img/noimg.jpg' 
                        alt="Preview" 
                        className="img-thumbnail mb-2"
                        width={350} height={300}
                        style={{objectFit: 'cover'}} 
                      />
                )}

                 {/* gắn sư kiện onChange handleImgPreview vào mục chooseimg   */}
                <input className="form-control" type="file" id="image" onChange={handleImgPreview}/>
            </div>


            {/* mã sản phẩm */}
            <div className="mb-3">
              <label htmlFor="product_code" className="form-label color_text_product">Mã sản phẩm</label>
              
              {/* thêm onChange với state setProductCode ghi nhan giá  trị nhập từ input de lay giá trị */}
              <input type="text" className="form-control" id="product_code" placeholder="Mời nhập mã sản phẩm" 
              onChange={(e)=>{setProductCode(e.target.value)}}/>
            </div>

            {/* tên sản phẩm */}
            <div className="mb-3">
              <label htmlFor="product_name" className="form-label color_text_product">Tên sản phẩm</label>
              <input type="text" className="form-control" id="product_name" placeholder="Mời nhập tên sản phẩm"
              onChange={(e)=>{setProductName(e.target.value)}} />
            </div>

            {/* mô tả ngắn */}
            <div className="mb-3">
              <label htmlFor="short_description" className="form-label color_text_product">Nội dung sort</label>
              <textarea className="form-control" id="short_description" rows={2} placeholder='vui lòng nhập nôi dụng mô tả ngắn ở đây' 
              onChange={(e)=>{setShortDescription(e.target.value)}} />
            </div>

            {/* mô tả chi tiết */}
            <div className="mb-3">
              <label htmlFor="description" className="form-label color_text_product">Nội dung chi tiết</label>
              <textarea className="form-control" id="description" rows={7} placeholder='vui lòng nhập nôi dụng mô tả chi tiết ở đây'
              onChange={(e)=>{setDescription(e.target.value)}}/>
            </div>



            {/***** nhập: giá nhập  - giá bán - số lượng sản phẩm.. chia cổ tỉ lệ 3 cột nằm chung hàng  *******/}
            <div className="row">
              {/* cột nhập nhập giá standard _code */}
               <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="standard_code" className="form-label color_text_product">Giá gốc</label>
                      <input type="number" className="form-control" id="standard_code" placeholder="Mời giá sản phẩm" 
                      onChange={(e)=>{setStandardCode(Number(e.target.value))}}/>
                  </div>
               </div>

               {/* cột nhập giá bán list_price */}
               <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="list_price" className="form-label color_text_product">Giá bán</label>
                      <input type="number" className="form-control" id="list_price" placeholder="Mời nhập giá bán sản phẩm" 
                      onChange={(e)=>{setListPrice(Number(e.target.value))}}/>
                  </div>
               </div>

                {/* cột nhập số lượng sản phẩm quantity_per_unit */}
                <div className="col-4">
                  <div className="mb-3">
                      <label htmlFor="quantity_per_unit" className="form-label color_text_product">Số lượng</label>
                      <input type="number" className="form-control" id="quantity_per_unit" placeholder="Mời số lượng sản phẩm" 
                      onChange={(e)=>{setQuantityPerUnit(Number(e.target.value))}}/>
                  </div>
               </div>
            </div><br/>





           {/**********cập nhạt trang  thái - cũng chia lam 3 cột trên một dòng **********/}
           <div className="row">
               {/* Switch check cột sản phẩm còn bán hay đã ngưng sủ dụng discontinued */}
               <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" id="discontinued" 
                      value="true" onChange={(e)=>{setDiscontinued(e.target.value === 'true')}}/>
                      <label className="form-check-label color_text_product" htmlFor="discontinued">Ngưng dùng?</label>
                  </div>
               </div>

                {/* Switch check làm nổi bật sản phẩm */}
              <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" id="is_featured" 
                      value="true" onChange={(e)=>{setIsFeatured(e.target.value === 'true')}}/>
                      <label className="form-check-label color_text_product" htmlFor="is_featured">Làm nổi bật ?</label>
                  </div>
               </div>

              {/* Switch check làm mới sản phẩm */}
              <div className="col-4">
                  <div className="form-check form-switch">
                      <input className="form-check-input" type="checkbox" role="switch" id="is_new" 
                      value="true" onChange={(e)=>{setIsNew(e.target.value === 'true')}}/>
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

                        <option value="">Mời chọn chuyên mục</option>
                           {/* cach call api do du lieu vao trong select */}
                           {listCategory.map((cat, index) => {
                              return(
                                  <option key={index} value={cat.id}>{cat.categoryName}</option>
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
                        value={supplierId ?? ''}// Nếu supplierId là null hoặc undefined thì sẽ dùng giá trị '' làm mặc định
                        className='form-control'  
                        onChange={(e)=>{setSupplierId(Number(e.target.value))}}
                       >

                        <option value="">Mời chọn nhà cung cấp</option>
                          {/* cach call api do du lieu vao trong select */}
                          {listSupplier.map((sup, index) => {
                              return(
                                  <option key={index} value={sup.id}>{sup.supplierName}</option>
                              )
                           })}
                      </select>
                  </div>
               </div>
            </div>

            {/* link quay về trang products */}
          {/* <Link href="/admin/products" className='btn btn-secondary'>
            <FontAwesomeIcon  icon={faArrowLeft} className='me-2' />
              Quay về trang products
          </Link>   */}

          {/* buton submit form save      */}
          <button className='btn btn-primary ms-2 float-end'>
            <FontAwesomeIcon icon={faSave} className='me-2' />
            Save
          </button>
          
        </form>



          {/************************ Toast thong bao trang thai - START ******************/}
              {/* <ToastContainer position="bottom-start" className="p-3" style={{ zIndex: 1 }} >
                <Toast onClose={() => setShowToast(false)} show={showToast} delay={3000} autohide bg ={conTextToast}>
                    <Toast.Header>
                       <strong className="me-auto">Thông báo</strong>
                    </Toast.Header>
                    <Toast.Body>{mesToast}</Toast.Body>
                </Toast>
            </ToastContainer> */}
          {/****************************Toast thong bao trang thai - END *******************/}

     </>
  )
}

export default CreateModal;




