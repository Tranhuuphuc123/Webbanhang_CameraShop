/*
  => khai bao import lib cua NextJs - 'use client' dùng để gọi API theo chuẩn NextJs 13 trở đi
  => useState, useEffect: dùng để khai báo states và lifecycle của component
   + useState: khai báo states ghi nhận trạng thái là các value cho biến state có tên
    là listProduct
   + useEffect: khai báo lifecycle của component
*/

'use client'
import { useState, useEffect } from "react";
import Image from "next/image";

import Link from "next/link";

//su dung lib axios call api ben client NextJs
import axios from "@/lib/axios";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faPlus, faTrash, faPenToSquare, faSave, faAngleDoubleLeft,
     faAngleDoubleRight, faAngleLeft, faAngleRight } from '@fortawesome/free-solid-svg-icons';

//import Modal từ lib Modal react-boostrap
import Modal from 'react-bootstrap/Modal';
// import Button from 'react-bootstrap/Button';

//khai bao import ModalContext mau context chung vao: luu ý import cai useModal hook ở qt3 
import {useModal} from "@/context/ModalContext"

//import useToast trong ToastContext(viết riêng chuẩn context ấy) để sử dụng cho trang create products
import { useToast } from '@/context/ToastContext';

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

const Products = ()=>{
    /********state cho modal add cho page product ******/
    // const [show, setShow] = useState(false);

    /****b1 - khởi tạo giá trị ban đầu (States) của các component bên trong trang****/
    //state cho hiển thị cá vlua trong table products ra client
    const [listProduct, setListProduct] = useState([]);

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
    const {openModal, closeModal, show, modalType} = useModal();

    
    //khai báo state tu useToast trong ToastContext truyền vào bien state
    const {showToast} = useToast();

    //state trạng thái ghi nhan id của cái products value cần xóa chọn đúng cái càn xóa qua id
    const [selectedId, setSelectedId] = useState(null)

    //state trạng thái xóa hàng loạt khi checkbox được chọn
    const [listSelectedId, setListSelectedId] = useState([]);


    /************************state quan ly trang thai phân  trang********************/
    //state lấy trang page hiện tại -> mac dinh la page 1
    const [currentPage, setCurrentPage] = useState(1);
    //state lay tong so page hien thi len giao dien
    const [totalPage, setTotalPage] = useState(1);
    //state lay tong so value(phan tu) /tat ca trang 
    const [totalElement, setTotalElement] = useState(0);
    // state pageSize: qui tinh mot page co nhieu phan tu khi hien thi len
    const pageSize = 1;


    /**********state trang thái tiềm kiếm theo tên******* */
    //state lưu trữ từ khóa tiềm kiếm
    const [searchQuery, setSearchQuery] = useState('');
    //state lưu trữ từ khóa tiềm kiếm sau khi đã bị delay
    const [debouncedSearchQuery, setDebouncedSearchQuery] = useState('');


    
    /************************************Các method tự viết*******************************/
    /**method xử lý ẩn/hiện modal delete form dúng voi id của value đc chọn**/
    const handleDeleteButtonClick = (e) => {
        //ghi nhận lấy id đúng id của vlaue mình muốn xóa
        const id = e.currentTarget.getAttribute('data-id')
        setSelectedId(id) 
        openModal('delete') //goi state openModal context -> show modal len vs type cua modal delete
    }

    /**method xử lý ẩn/hiện nút deleteAll button - xóa hàng loạt khi tick vào ô checkbox ứng vói id 
     * tương ứng thì nó mới hiện cái button deleteAll còn bt thì nó ẩn đi**/
    const handleDeleteAllCheckbox = (e) => {
        //ghi nhan value khi tick vao o checkbox la id
        const id = e.target.value;
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
    const handleOpenEditModal = (e) => {
         //ghi nhận lấy id đúng id của vlaue mình muốn edit
         const id = e.currentTarget.getAttribute('data-id')
         setSelectedId(id) 
        openModal('edit')
    }




    /****b2 - tiến hành nhờ axios gửi request đến API để lấy giá trị render ra giao diện *****/
    /*useEffect thực hiện side effect là gọi API khi component được render lần đầu tiên.
    chỉ chạy một lần duy nhất sau khi component được render lần đầu tiên
    >>chú thích thêm về useEffect<<:
    + side effect: là những tác động bên ngoài component,
        như gọi API, thay đổi DOM, thay đổi state
    + useEffect: là một lifecycle của component, thay thế 
    cho các lifecycle cũ như componentDidMount, componentDidUpdate, 
    componentWillUnmount
    + lifecycle là các hàm mà React/Next gọi tự động khi xảy ra 
    các sự kiện như:
    + khai báo useEffect gồm có hai phẩn là: 
        ++ () => {... }: Một hàm callback chứa logic side effect đc code ỏ đó
        ++ dấu "[]": Có nghĩa là useEffect chỉ chạy một lần duy nhất
        sau khicomponent được render lần đầu tiên. 
     + [listProduct]: useEffect sẽ chạy mỗi khi giá trị của listProduct thay đổi  
     ====> thay thees [listPRoduct] thanh [currentPage] de phan trang ghi nhan su thay doi
     khi hien thi lai trang -> pv vu chuc nang phan trang
    */

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
        const fetchProduct = async ()=>{
            const res = await axios.get(`shop-products`, {
                params: {
                    page: currentPage,
                    size: pageSize,
                    search: debouncedSearchQuery //điều kiện tiềm kiếm
                }
            });
            // lap dieu kien kiem tra tranh null khi tiem kiem theo ten va theo ma
            if(res.data.data =null){
                setListProduct([]);    
            }else{
                setListProduct(res.data.data); //update ds value tren csdl vao table product
            }

            setTotalPage(res.data.totalPage); //lay tong so page cap nhat ngay
            setTotalElement(res.data.totalElement); //lay tong so value cap nhat ngay
        }
        fetchProduct();
    }, [currentPage, debouncedSearchQuery]);


   
    
    //tạo mảng chứa số trang để hiển thị ra giao diện
    const pageNumbers = []
    for(let i = 1; i<= totalPage; i++){
        pageNumbers.push(i)
    }

    /**method handlePageChange: xu ly nguoi dung thay doi trang**/
    const handlePageChange = (page) => {
        if(page < 1 || page > totalPage) return;
        setCurrentPage(page);
    }


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
                            {/* value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}: 
                             -> ý nghĩa:   
                                + value: giá trị của input
                                + onChange: hàm xử lý sự kiện khi người dùng nhập vào input
                                + e.target.value: giá trị của input được nhập vào ghi nhận lại
                                + setSearchQuery(e.target.value): cập nhật giá trị của state searchQuery
                                + e: là event khi người dùng nhập vào input 
                            */}
                            <input type="text" placeholder="Nhập tên" className="form-control w-50"
                              value={searchQuery}
                              onChange={(e) => setSearchQuery(e.target.value)} />
                        </div>
                    </div>
                </div>
            </div>
             

            {/* giao diện table products */}
            <div className="card p-3 manage-employees">
                <div className="row align-items-center mb-3 mx-1">
                    <div className="col-sm-6 p-0 ">
                        <h5 className="ml-lg-2">Manage Products</h5>
                    </div>
                    <div className="col-sm-6 p-0 text-end">
                        {/* <Link href="#" className="btn btn-success led-toggler me-2" data-toggle="modal">
                            <FontAwesomeIcon icon={faPlus} className="fa-fw" />
                            <span>Add New Employees</span>
                        </Link> */}
                        <button className="btn btn-success me-2" onClick={() => openModal('create')}>
                            <FontAwesomeIcon icon={faPlus} className="fa-fw" />
                            <span>Add New Employees</span>
                        </button>
                        
                        {/* xét đk là nếu state listSelectedId có độ dài lớn hơn 0: tức là có tick ào ô check 
                        thì mới hiện nút delete all */}
                        {listSelectedId.length > 0 &&
                            <button className="btn btn-danger led-toggler" data-toggle="modal" 
                            onClick={handelBatchDeleteAll}>
                                <FontAwesomeIcon icon={faTrash} className="fa-fw" />
                                <span>Delete All</span>
                            </button>
                        }
                    </div>
                </div>

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
                                        <Image src={API_UPLOAD_URL +  p.image} alt={p.productName} width={120} height={120}/>
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
                                        {/* data-id: giúp ghi nhận id của sản phẩm mà mình chọn delete */}
                                        <button className="btn btn-danger btn-sm me-2 mb-2" data-id={p.id} 
                                        onClick={handleDeleteButtonClick}> 
                                             <FontAwesomeIcon icon={faTrash} className="fa-fw" />
                                             Xóa
                                        </button>
                                        <button className="btn btn-warning btn-sm me-2"  data-id={p.id} 
                                        onClick={handleOpenEditModal}>  
                                            <FontAwesomeIcon icon={faPenToSquare}className="fa-fw"/>
                                             Sữa
                                         </button>
                                    </td>
                                </tr>
                            )
                        })}

                    </tbody>
                </table>

                {/* giao dien xu ly phan trang */}
                <div className="pagination-container d-flex justify-content-between align-items-center">
                    <div className="pagination-info">
                        Trang {currentPage}/{totalPage} - Tổng: 
                        {totalElement} sản phẩm
                    </div>
                    <div className="pagination-control">
                        <button className="pagination-button" disabled={currentPage==1}
                        onClick={() => handlePageChange(1)}>
                            <FontAwesomeIcon icon={faAngleDoubleLeft} className="fa-fw"/>
                        </button>
                        <button className="pagination-button" disabled={currentPage==1}
                        onClick={() => handlePageChange(currentPage - 1)}>
                            <FontAwesomeIcon icon={faAngleLeft} className="fa-fw"/>
                        </button>

                        {/* pagination-button ${currentPage == page ? 'active' : ''} 
                        -> ý nghĩa: nếu currentPage bằng page thì nó sẽ có class là active
                        chủ yếu để tô đậm trang đang chọn
                        */}
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




            {/* tạo Modal create products theo cấu trúc của lib react-bootstrap */}
            {/* size ="lg" */}
            <Modal show={show && modalType === 'create'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Thêm mới sản phầm</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                      <CreateProductForm listProduct={listProduct} setListProduct={setListProduct}/>
                </Modal.Body>

                {/* <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>Close</Button>
                    <Button variant="primary">
                         <FontAwesomeIcon icon={faSave} className='me-2'/> 
                         Save changes
                    </Button>
                </Modal.Footer> */}
            </Modal>


            {/* Modal form delete */}
            <Modal show={show && modalType === 'delete'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Xác nhận xóa {selectedId}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <DeleteProductForm id={selectedId} listProduct={listProduct} 
                    setListProduct={setListProduct}/>
                </Modal.Body>
            </Modal>


             {/* Modal form batch-delete xoa hang loat id cung luc */}
             <Modal show={show && modalType === 'batch-delete'} onHide={closeModal} >
                <Modal.Header closeButton>
                     <Modal.Title>Xác nhận xóa hàng loạt {listSelectedId.join(', ')} </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <BatchDelete ids={listSelectedId} listProduct={listProduct} 
                    setListProduct={setListProduct} setListSelectedId={setListSelectedId}/>
                </Modal.Body>
            </Modal>


            {/* Modal form edit cua page Product -> edit value cua record products */}
            <Modal show={show && modalType === 'edit'} onHide={closeModal} >
                <Modal.Header closeButton>
                    <Modal.Title>Edit Product - edit value {selectedId}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <UpdateModal id={selectedId} listProduct={listProduct} 
                    setListProduct={setListProduct}/>
                </Modal.Body>
            </Modal>

        </>
    )
}

export default Products;



// 10' 23"