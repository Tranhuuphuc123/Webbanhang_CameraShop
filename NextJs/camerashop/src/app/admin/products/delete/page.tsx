/*************** thiết kế form giao diện(ruột form) cho modal delete****************/
'use client';
//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';
//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import interface  types định kiểu dữ liệu cho dữ liệu cho page props del Product.
import { DeleteProductPropsTypes, ModalContextType, ToastContextType} from "@/types/TsSetup";

//import lib axios xử lý call api co mục select category và supplier id
import axios from '@/lib/axios';

const DeleteProductForm: React.FC<DeleteProductPropsTypes> = ({id, onReload}) => {

    //khai báo state tu useToast trong ToastContext truyền vào bien state
    const {showToast} = useToast() as ToastContextType;

    //state trang thai dung voi useModal cua ModalContext:
    const {closeModal} = useModal() as ModalContextType;
    
    
   /** mehod xử lý xóa **/
    const handleDeleteClick = async (e: React.FormEvent<HTMLFormElement>) => {
         //ngăn sk mặc đinh khi nhấn nút submit của trình duyệt
         e.preventDefault()

         // gọi  và nhờ axios xử lý call api thục thi delete
         const deleteFormProduct = async ()=>{
           try{   
            //axios lib -> goi api thực thi xóa 
              const response = await axios.delete("shop-products/delete/" + id) 
   
              //goi showToast vao de su dung hien thi TOast trong useEffect
              showToast(response.data.msg, 'success')

              if (onReload) {
                onReload();
              }

              //close modal khi xóa thành công -xóa xong đóng hộp thoại xóa di
              closeModal()

              // // Cập nhật danh sách sản phẩm sau khi xóa -> refresh lại trang
              // const newListProduct = listProduct.filter((p) => !id.includes(p.id))
             
              //cập nhật lại trạng thái 
              // setListProduct(newListProduct)            
           }catch(error:any){
             const errorMessage = error.response?.data?.message || 'Có lỗi khi xóa sản phẩm!';
             showToast(errorMessage, 'danger');
           }
         }
   
         //gọi thực thi hàm xử lý createFormProducts
         await deleteFormProduct()
       }


   /**trả lại giao diện cho form delete**/    
    return <>
      <p>Bạn có chắc muốn xóa dữ liệu này khong {id}? </p>
      <div  className="d-flex justify-content-end">
          <button className="btn btn-danger" onClick={handleDeleteClick}>Đồng ý</button>
      </div>
    </>  
}

export default DeleteProductForm;

