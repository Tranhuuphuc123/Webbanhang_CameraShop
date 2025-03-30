/****form ruột của modal deleteAll form - xóa nhiều record dựa trên id từng record cùng lúc****/

'use client';
//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';
//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import lib axios xử lý call api co mục select category và supplier id
import axios from '@/lib/axios';

const BatchDelete = ({ids, listProduct, setListProduct, setListSelectedId}) => {
    //khai báo state tu useToast trong ToastContext truyền vào bien state
    const {showToast} = useToast();

    //state trang thai dung voi useModal cua ModalContext:
    const {openModal, closeModal, show, modalType} = useModal();


    /**method xu ly xoa hang loat cac id duoc chon**/
    const handleBatchDelete =  async (e) => {
        //ngăn sk mặc đinh khi nhấn nút submit của trình duyệt
        e.preventDefault()

        //call api batch-delete
        const ApiDeleteBatch = async () => {
            try {
                // Gọi API xóa hàng loạt
                const res = await axios.delete("shop-products/batch-delete", {
                  data: ids 
                });
      
                // Hiển thị thông báo thành công
                showToast(res.data.message || "Xóa sản phẩm thành công!", "success");
    
                // Đóng modal sau khi xóa thành công
                closeModal();
                
               // Cập nhật danh sách sản phẩm sau khi xóa -> refresh lại trang
               const newListProduct = listProduct.filter((p) => !ids.includes(p.id))
             
               //cập nhật lại trạng thái listProduct
               setListProduct(newListProduct)   
               
               // cập nhật và clear bỏ trạng thái  của selctedIds
               setListSelectedId([])
               
            } catch (error) {
                const errorMessage = error.response?.data?.message || "Có lỗi xảy ra khi xóa!";
                showToast(errorMessage, "danger");
            }
        }

        await ApiDeleteBatch()
    }
    
   /**trả lại giao diện cho form delete**/    
   return <>
        <p>Bạn có chắc muốn xóa dữ các dữ liệu {ids.join(', ')} này khong ? </p>
        <div  className="d-flex justify-content-end">
            <button className="btn btn-danger" onClick={handleBatchDelete}>Đồng ý</button>
        </div>
    </>  

}

export default BatchDelete;


