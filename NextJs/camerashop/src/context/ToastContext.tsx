/**  mau Toast dung chung cho project nay - thong bao thanh cong hay that bai **/
'use client';
// import  lib cho việc sử dụng khai báo quản lý tập trung context
import { createContext, useContext, useState } from "react";

//import lib Toast/ToastContainer từ react-bootstrap
// import Toast from 'react-bootstrap/Toast';
// import ToastContainer from 'react-bootstrap/ToastContainer'; 
import {Toast, ToastContainer} from 'react-bootstrap';




/********************************QUI TRÌNH LÀM**************************************** */

/******quy trinh 1 giai thichs:  tạo một vùng chứa context để lưu trữ trang
 *  thai thông tin toast (bat/tat cua message thong bao ra Toast)*****/
const ToastContext = createContext();




/** qui trình 2: tạo provider để xử lý bên trong ToastContext:
 * => vai trò provider: là nơi chứa các state và method xử lý sự kiện của Toast
 * => phân bố bố trí cấu trúc thiết kế bố trí xử lý sự kiện cho Toast
 */
const ToastProvider = ({children}) => {
    /*tao state: show (an/hien cho Toast), msgToast(thong bao ra Toast) va 
    variant(bien the ngu canh mau sac thong bao thanh cong/that bai)*/
    const [show, setShow] = useState(false);
    const [msgToast, setMsgToast] = useState('');
    const [variant, setVariant] = useState('primary');

    //method xu ly hien thi Toast: msg thong la nd gi vau sac variant tuongung: xanh ok, do la that bai
    const showToast = (msg, variant) => {
        setShow(true);
        setMsgToast(msg);
        setVariant(variant);
    }


    //tra ve giao dien cua ToastProvider mong muon
    return (
        <ToastContext.Provider value={{showToast, show}}>
            {children}
            <ToastContainer  position="top-start" className="p-3" style={{ zIndex: 120000 }}>
                <Toast show={show} onClose={() => setShow(false)} bg={variant} delay={3000} autohide>
                    <Toast.Header>
                        <strong className="me-auto">Thông báo</strong>
                    </Toast.Header>
                    <Toast.Body>{msgToast}</Toast.Body>
                </Toast>
            </ToastContainer>
        </ToastContext.Provider>
    )
}
export default ToastProvider;




/***************tui trinh 3: tao hooks de su dung Toasts******************* */
// chô hooks này là chỗ sử dùng cho các component khác muốn sử dụng Toast
export const useToast = () => useContext(ToastContext);