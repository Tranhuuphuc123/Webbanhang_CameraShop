/************xây dựng form modal chuẩn context dùng chung toàn cục********* */
'use client';
import { createContext, useContext, useState } from "react";

/***************qui trinh tao model form chuan context nhu sau************* */

/********* qui trinh 1: tao context*****************/
const ModalContext = createContext();



/**********qui trinh 2: provider xu ly giao dien modal tong the*********************** */
//=> bao boc container thg dc viet trong cac layout.tsx
const ModalProvider = ({children}) => {
    //state trang thai show/hide cua modal
    const [show, setShow] = useState(false);
    //thêm modalType để xác định modal nào đang mở: modal create or delete or edit....
    const [modalType, setModalType] = useState(null)
    
    //method OpenModal: hien thi modal
    const openModal = (type) => {
        setModalType(type); // Lưu loại modal
        setShow(true);
    };
    //method CloseModal: an modal
    const closeModal = () => {
        setShow(false);
        setModalType(null); // Reset lại modalType khi đóng modal
    };

    //tra ve giao dien cua ModalProvider mong muon
    return (
        <ModalContext.Provider value={{openModal, closeModal, show, modalType }}>
            {children}
        </ModalContext.Provider>
    )
}
export default ModalProvider;
 




/**********qui trinh 3: tao hook dieu phoi xu ly******************************** */
// => de su dung trong cac component
export const useModal = () => {
    return useContext(ModalContext);
}
