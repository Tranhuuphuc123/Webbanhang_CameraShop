/*
  => khai bao import lib cua NextJs - 'use client' dùng để gọi API theo chuẩn NextJs 13 trở đi
  => useState, useEffect: dùng để khai báo states và lifecycle của component
   + useState: khai báo states ghi nhận trạng thái là các value cho biến state có tên
    là listProduct
   + useEffect: khai báo lifecycle của component
*/

/* qui trinh code nexttj -> sắp xếp code theo bó cục 
 1. khai báo import lib 
 2. khai báo const function chinh
   +a/ khai báo state
   +b/ khai báo function - method  xử lý sự kiện 
   +c/ khai báo useEffect  
   +d/ return giao diện render HTML/CSS

 3. export default function chinh xuât function chính ra 
*/

'use client'
import { useState, useEffect } from "react";
import Image from "next/image";
import React from "react";

//import interface types định kiểu dữ liệu cho dữ liệu sản phẩm products..
import { ProductTypes, ApiResponseTypes, ModalContextType } from "@/types/TsSetup";

//su dung lib axios call api ben client NextJs
import axios from "@/lib/axios";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faPlus, faTrash, faPenToSquare, faAngleDoubleLeft,
     faAngleDoubleRight, faAngleLeft, faAngleRight } from '@fortawesome/free-solid-svg-icons';

//import Modal từ lib Modal react-boostrap
import Modal from 'react-bootstrap/Modal';
// import Button from 'react-bootstrap/Button';

//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"


//import page form create product(form create tự thiết kế bằng bootstrap)
import CreateProductForm from '@/app/admin/products/create/page';
//import page form delete product
import DeleteProductForm from '@/app/admin/products/delete/page';
//import page form delete all product
import BatchDelete from '@/app/admin/products/batch_delete/page';
//import form edit products
import UpdateModal from '@/app/admin/products/edit/page';


//make variale api url file upload img
const API_UPLOAD_URL = "http://localhost:8080/uploads/";

const Products = () => {
    /********state cho modal add cho page product ******/
    /****b1 - khởi tạo giá trị ban đầu (States) của các component bên trong trang****/
    //state cho hiển thị cá vlua trong table products ra client
    const [listProduct, setListProduct] = useState<ProductTypes[]>([]);

    //state trạng thái ghi nhan id của cái products value cần xóa chọn đúng cái càn xóa qua id
    const [selectedId, setSelectedId] = useState<string | null>(null)

    //state trạng thái xóa hàng loạt khi checkbox được chọn
    //number[]: định kiểu types trong tsx với [] là dùng để khai báo cho type là một mảng các value 
    const [listSelectedId, setListSelectedId] = useState<string[]>([]);


    /************************state quan ly trang thai phân  trang********************/
    //state lấy trang page hiện tại -> mac dinh la page 1
    //<type>: định kiểu dữ liệu dùng trong tsx
    const [currentPage, setCurrentPage] = useState<number>(1);
    //state lay tong so page hien thi len giao dien
    const [totalPage, setTotalPage] = useState<number>(1);
    //state lay tong so value(phan tu) /tat ca trang 
    const [totalElement, setTotalElement] = useState<number>(0);
    // state pageSize: qui tinh mot page co nhieu phan tu khi hien thi len
    const pageSize = 2;


    /**********state trang thái tiềm kiếm theo tên******* */
    //state lưu trữ từ khóa tiềm kiếm
    const [searchQuery, setSearchQuery] = useState<string>('');
    //state lưu trữ từ khóa tiềm kiếm sau khi đã bị delay
    const [debouncedSearchQuery, setDebouncedSearchQuery] = useState<string>('');
    

        /**state trang thai dung voi useModal cua ModalContext: 
     * => lưu ý: openModal và closeMOdal trong modalcontext là một hàm method xử lý
     * bật và tắt modal nên khỏi cần viết lại các method như handleShow/Hide chi nữa
     * vì bản chât openModal closeModal là method rồi đay page này ta chỉ gọi đến nó 
     * kiểu kế thừa nó và sử dụng thui vì modal context đã viết định nghĩa chung hết rồi
     * =>  monoType đẻ chỉ đinh modal là create/delete/edit.. khi mở giúp có thể tái sử dụng 
     * nhiều lần modalContect mà khong bị lỗi dó có chỉ định cụ thẻ là đang open modalCOntext
     * là type nào(create/delete/edit... cụ thể tránh hiểu lầm mở lần nó hiểu sai là mở 
     * cùng lúc tất cả modal create/delete và edit một lượt nếu không có type phân loại)
     * **/
    //as ModalContextType: định kiểu dữ liệu cho modalContextType
    const {openModal, closeModal, show, modalType} = useModal() as ModalContextType;


    
    /************************************Các method tự viết*******************************/
    /**method xử lý ẩn/hiện modal delete form dúng voi id của value đc chọn**/
    /*e: React.MouseEvent<HTMLButtonElement>: định kiểu dữ liệu của event click
    vì sự kiện là button nên event nó là click mouse event*/
    const handleDeleteButtonClick = (e: React.MouseEvent<HTMLButtonElement>) => {
        //ghi nhận lấy id đúng id của vlaue mình muốn xóa
        const id = e.currentTarget.getAttribute('data-id')
        setSelectedId(id) 
        openModal('delete') //goi state openModal context -> show modal len vs type cua modal delete
    }

    /**method xử lý ẩn/hiện nút deleteAll button - xóa hàng loạt khi tick vào ô checkbox ứng vói id 
     * tương ứng thì nó mới hiện cái button deleteAll còn bt thì nó ẩn đi**/
    /*(e: React.ChangeEvent<HTMLInputElement>): định kiểu dữ liệu của event click -> vì sự kiện là thẻ 
    input nên no có event là onchange*/
    const handleDeleteAllCheckbox = (e: React.ChangeEvent<HTMLInputElement>) => {
        //ghi nhan value khi tick vao o checkbox la id
        const id = (e.target.value);
        /*giải thích code:
         + prev: giá trị biến hiện tại của state listSelectedId
         + prev.includes(id): kiểm tra xem id có nằm trong mảng listSelectedId hay không
         + prev.filter((item) => item != id):  nếu id có trong mảng listSelectedId thì nó sẽ bị filter 
         lọc ra 
         +  [...prev, id] : thêm id vào mảng listSelectedId nếu id không nằm trong mảng prev
         --> (ý nghĩa đoạn sau? là nó giúp kiêm tra khi tick vào ô checkbox thì nó ghi nhận và hiển ra nut deleteall
         còn nếu bỏ tick nó ẩn đi nút deleteall vì lúc này id không nằm trong mảng listSelectedId)
        */
         setListSelectedId((prev) => prev.includes(id) ? prev.filter((item) => item != id) : [...prev, id] )
    }

    /**method xử lý mở modal deleteAll form khi click vào button deleteAll**/
    const handelBatchDeleteAll = () => {
        openModal('batch-delete')
    }

    /**method xử lý ẩn/hiện modal editproduct Form khi clickl vào button edit**/
    /*e: React.MouseEvent<HTMLButtonElement>: định kiểu dữ liệu của event click
    khi sự kiện là button click thì event nó là click mouse event*/
    const handleOpenEditModal = (e: React.MouseEvent<HTMLButtonElement>) => {
         //ghi nhận lấy id đúng id của vlaue mình muốn edit
         const id = e.currentTarget.getAttribute('data-id')
         setSelectedId(id) 
        openModal('edit')
    }

    //method handReload: reload lại trang sau khi create/edit/delete xong 
    const handleReload = () => {
        fetchProduct();
    }

     
    /******************xử lý phân trang**************************** */
    //tạo mảng chứa số trang để hiển thị ra giao diện
    const pageNumbers = []
    for(let i = 1; i<= totalPage; i++){
        pageNumbers.push(i)
    }

    /**method handlePageChange: xu ly nguoi dung thay doi trang**/
    const handlePageChange = (page: number) => {
        if(page < 1 || page > totalPage) return;
        setCurrentPage(page);
    }





    /***method fetchProduct: gọi api xử lý phân trang và search: theo ten va theo ma***/
    //ApiResponseTypes: định kiểu dữ liệu trả về từ api
    const fetchProduct = async ()=>{
        const res = await axios.get<ApiResponseTypes>(`shop-products`, {
            params: {
                pageNumber: currentPage,
                pageSize: pageSize,
                search: debouncedSearchQuery //điều kiện tiềm kiếm
            }
        });
        // lap dieu kien kiem tra tranh null khi tiem kiem theo ten va theo ma
        if(res.data.data == null){
            setListProduct([]);    
        }else{
            setListProduct(res.data.data); //update ds value tren csdl vao table product
        }

        setTotalPage(res.data.totalPage); //lay tong so page cap nhat ngay
        setTotalElement(res.data.totalElement); //lay tong so value cap nhat ngay
    }


    //useEffect thực setTimeOUt -> sau 500ms thì mới thực hiện search theo api
    useEffect(() => {
        const delayInputTimeoutId = setTimeout(() => {
            setDebouncedSearchQuery(searchQuery);
            setCurrentPage(1); // reset phan trang về page 1
        }, 500);
        return () => clearTimeout(delayInputTimeoutId);
    }, [searchQuery]);
    

    //  useEffect: thưc thi goi api xử lý phân trang và search: theo ten va theo ma
    useEffect(() => {
        fetchProduct();
    }, [currentPage, debouncedSearchQuery]);


  
    return (
        //mục này mình đưa trang dashboard vào đay
        <>
            <div className="mb-3">
                <h3>Danh sách sản phẩm</h3>
            </div>

            {/* mục giao diện chức năng tiềm kiếm trang product search */} 
            <div className="card p-3 manage-employees">
                <div className="row align-items-center mb-3 mx-1">
                    <div className="col-sm-12 p-0">
                        <h5 className="ml-lg-2">Bộ lộc tiềm kiếm</h5>
                        <div className="form-group">
                            <label className="me-2">Tên sản phẩm</label>
                            <input type="text" placeholder="Nhập tên" className="form-control w-100 w-md-50"
                              value={searchQuery}
                              onChange={(e) => setSearchQuery(e.target.value)} />
                        </div>
                    </div>
                </div>
            </div>
             

            {/* giao diện table products */}
            <div className="card p-3 manage-employees">
                <div className="row align-items-center mb-3 mx-1">
                    <div className="col-12 col-sm-6 p-0 mb-2 mb-sm-0">
                        <h5 className="ml-lg-2">Manage Products</h5>
                    </div>
                    <div className="col-12 col-sm-6 p-0 text-start text-sm-end">
                        <button className="btn btn-success me-2 mb-2 mb-sm-0" onClick={() => openModal('create')}>
                            <FontAwesomeIcon icon={faPlus} className="fa-fw" />
                            <span>Add New Employees</span>
                        </button>
                        
                        {listSelectedId.length > 0 &&
                            <button className="btn btn-danger led-toggler" data-toggle="modal" 
                            onClick={handelBatchDeleteAll}>
                                <FontAwesomeIcon icon={faTrash} className="fa-fw" />
                                <span>Delete All</span>
                            </button>
                        }
                    </div>
                </div>

                <div className="table-responsive">
                    <table className="table led-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Image</th>
                                <th>Product</th>
                                <th>Short Description</th>
                                <th>Standard Cost</th>
                                <th>List Price</th>
                                <th>Quantity Per Unit</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {listProduct.map((p, index) => {
                                return (
                                    <tr key={index}>
                                        <td>
                                            <input type="checkbox" name="checkProduct[]" value={p.id} 
                                            onChange={handleDeleteAllCheckbox}/> 
                                        </td>
                                        <td>
                                            <Image src={API_UPLOAD_URL +  p.image} alt={p.productName} width={120} height={120}
                                            className="img-fluid"/>
                                        </td>
                                        <td>
                                            Mã: {p.productCode} <br/>
                                            Tên: {p.productName}
                                        </td>
                                        <td>{p.shortDescription}</td>
                                        <td>{p.standardCost}</td>
                                        <td>{p.listPrice}</td>
                                        <td>{p.quantityPerUnit}</td>
                                        <td>
                                            <div className="d-flex flex-column flex-sm-row gap-2">
                                                <button className="btn btn-danger btn-sm" data-id={p.id} 
                                                onClick={handleDeleteButtonClick}> 
                                                    <FontAwesomeIcon icon={faTrash} className="fa-fw" />
                                                    Xóa
                                                </button>
                                                <button className="btn btn-warning btn-sm" data-id={p.id} 
                                                onClick={handleOpenEditModal}>  
                                                    <FontAwesomeIcon icon={faPenToSquare}className="fa-fw"/>
                                                    Sữa
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                )
                            })}
                        </tbody>
                    </table>
                </div>

                {/* giao dien xu ly phan trang */}
                <div className="pagination-container d-flex flex-column flex-sm-row justify-content-between align-items-center gap-3">
                    <div className="pagination-info text-center text-sm-start">
                        Trang {currentPage}/{totalPage} - Tổng: 
                        {totalElement} sản phẩm
                    </div>
                    <div className="pagination-control d-flex justify-content-center">
                        <button className="pagination-button" disabled={currentPage==1}
                        onClick={() => handlePageChange(1)}>
                            <FontAwesomeIcon icon={faAngleDoubleLeft} className="fa-fw"/>
                        </button>
                        <button className="pagination-button" disabled={currentPage==1}
                        onClick={() => handlePageChange(currentPage - 1)}>
                            <FontAwesomeIcon icon={faAngleLeft} className="fa-fw"/>
                        </button>

                        {pageNumbers.map((page) => {
                            return (
                                <button key={page} className={`pagination-button ${currentPage == page ? 'active' : ''}`}
                                    onClick={() => handlePageChange(page)}>{page}
                                </button>
                            )
                        })}

                        <button className="pagination-button" disabled={currentPage == totalPage}
                        onClick={() => handlePageChange(currentPage + 1)}>
                            <FontAwesomeIcon icon={faAngleRight} className="fa-fw"/>
                        </button>
                        <button className="pagination-button" disabled={currentPage == totalPage}
                        onClick={() => handlePageChange(totalPage)}>
                            <FontAwesomeIcon icon={faAngleDoubleRight} className="fa-fw"/>
                        </button>
                    </div>
                </div>
            </div> 

            {/* Modals */}
            <Modal show={show && modalType === 'create'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Thêm mới sản phầm</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                      <CreateProductForm onReload={handleReload}/>
                </Modal.Body>
            </Modal>

            <Modal show={show && modalType === 'delete'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Xác nhận xóa {selectedId}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <DeleteProductForm id={selectedId ?? ''} onReload={handleReload}/>
                </Modal.Body>
            </Modal>

            <Modal show={show && modalType === 'batch-delete'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Xác nhận xóa hàng loạt {listSelectedId.join(', ')} </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <BatchDelete ids={listSelectedId} listProduct={listProduct} 
                    setListProduct={setListProduct} setListSelectedId={setListSelectedId}/>
                </Modal.Body>
            </Modal>

            <Modal show={show && modalType === 'edit'} onHide={closeModal} >
                <Modal.Header closeButton>
                    <Modal.Title>Edit Product - edit value {selectedId}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <UpdateModal id={selectedId ?? ''} onReload={handleReload}/>
                </Modal.Body>
            </Modal>
        </>
    )
}



export default Products;
