/****form ruột của modal deleteAll form - xóa nhiều record dựa trên id từng record cùng lúc****/

'use client';
//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';
//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import interface  types định kiểu dữ liệu cho dữ liệu cho page props del Product.
import { ModalContextType, ToastContextType, BatchDeletePropsTypes} from "@/types/TsSetup";

//import lib axios xử lý call api co mục select category và supplier id
import axios from '@/lib/axios';

const BatchDelete: React.FC<BatchDeletePropsTypes> = ({ids, onReload}) => {
    //khai báo state tu useToast trong ToastContext truyền vào bien state
    const {showToast} = useToast() as ToastContextType;

    //state trang thai dung voi useModal cua ModalContext:
    const {closeModal} = useModal() as ModalContextType;


    /**method xu ly xoa hang loat cac id duoc chon**/
    const handleBatchDelete =  async (e: React.FormEvent<HTMLFormElement>) => {
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
    
                // Đóng modal sau khi xóa thành công
                closeModal();
                
            //    // Cập nhật danh sách sản phẩm sau khi xóa -> refresh lại trang
            //    const newListProduct = listProduct.filter((p) => !ids.includes(p.id))
             
            //    //cập nhật lại trạng thái listProduct
            //    setListProduct(newListProduct)   
               
            //    // cập nhật và clear bỏ trạng thái  của selctedIds
            //    setListSelectedId([])
               
            } catch (error:any) {
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


