"use client";
//import link của next/link
import Link from "next/link";
import Image from "next/image";

//import button của lib react bootstrap
import Button from "react-bootstrap/Button";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faBell,
  faBoxesStacked,
  faCircleQuestion,
  faHouse,
  faNewspaper,
  faSearch,
  faTags,
  faUser,
  faCartShopping,
} from "@fortawesome/free-solid-svg-icons";

//import modal context react bootstrap
import Modal from "react-bootstrap/Modal";
import { useModal } from "@/context/ModalContext";

//import component OffCanvasExample: lib giúp hiện thị form từ các hướng khi nhấn sự kiện nào đó
import OffCanvasExample from "@/components/OffCanvasExample";

//import page login
import Login from "@/app/client/login/page";

//usePathname: hook của next/navigation giúp lấy đường dẫn URL hiện tại của trang web
import { usePathname } from "next/navigation";

//useEffect: hook của React giúp thực thi các side effect (như call API, DOM updates) sau khi component render
import { useEffect } from "react";
import { ModalContextType } from "@/types/TsSetup";

//import page cart
import CartPage from "@/app/client/cart/page";

const Header = () => {
  //as ModalContextType: định kiểu dữ liệu cho modalContextType
  const { openModal, closeModal, show, modalType } =
    useModal() as ModalContextType;

  // thực hiện hành động là: Đóng modal context form login khi route Link thay đổi sang trang mới
  const pathname = usePathname();
  useEffect(() => {
    closeModal();
    // eslint-disable-next-line
  }, [pathname]);

  return (
    <>
      {/* <!-- menu lenh --> */}
      <nav className="pv-menu navbar navbar-expand-lg navbar-dark">
        <div className="container-fluid">
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#pvNavbar"
            aria-controls="pvNavbar"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="pvNavbar">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link active" href="/client">
                  <FontAwesomeIcon icon={faHouse} className="fa-solid me-2" />{" "}
                  Trang chủ
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" href="/client/products">
                  <FontAwesomeIcon
                    icon={faBoxesStacked}
                    className="fa-solid me-2"
                  />{" "}
                  Sản phẩm
                </Link>
              </li>

              <li className="nav-item">
                <a className="nav-link" href="#">
                  <FontAwesomeIcon icon={faTags} className="fa-solid me-2" />{" "}
                  Khuyến mãi
                </a>
              </li>

              <li className="nav-item">
                <a className="nav-link" href="#">
                  <FontAwesomeIcon
                    icon={faNewspaper}
                    className="fa-solid me-2"
                  />{" "}
                  Tin tức
                </a>
              </li>

              <li className="nav-item">
                <a className="nav-link" href="#">
                  <FontAwesomeIcon
                    icon={faCircleQuestion}
                    className="fa-solid me-2"
                  />{" "}
                  Hỗ trợ
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

      {/* <!-- Main Header --> */}
      <div className="pv-header-main py-2 bg-white border-bottom">
        <div className="container-fluid">
          <div className="row align-items-center p-2">
            <div className="col-6 col-md-2 text-center text-md-start mb-2 mb-md-0">
              <Image
                src="/assets/client/img/anhtemp/logo.png"
                alt="Logo"
                width={120}
                height={40}
              />
            </div>

            {/* <!-- Danh mục sản phẩm button --> */}
            <div className="col-6 col-md-3 mb-2 mb-md-0 d-flex align-items-center">
              {/* <button
                className="btn d-flex align-items-center"
                style={{
                  borderRadius: 8,
                  color: "#7b8190",
                  borderColor: "#e3e6ed",
                  background: "#fff",
                  fontWeight: 500,
                }}
              >
                <FontAwesomeIcon icon={faBars} className="fa-solid me-2" /> Danh
                mục
              </button> */}

              <Button
                variant="link"
                className="border p-3 text-dark text-decoration-none bg-white"
              >
                <FontAwesomeIcon icon={faBars} className="fa-solid me-2" /> Danh
                mục
              </Button>
            </div>

            {/* <!-- Tìm kiếm sản phẩm --> */}
            <div className="col-12 col-md-6 mb-2 mb-md-0">
              <form className="d-flex w-100">
                <input
                  className="form-control me-2"
                  type="search"
                  placeholder="Nhập từ khoá cần tìm"
                  aria-label="Search"
                />
                <button className="btn btn-light border pe-1" type="submit">
                  <FontAwesomeIcon icon={faSearch} className="fa-solid me-2" />
                </button>
              </form>
            </div>

            <div className="col-6 col-md-4 text-end">
              <a href="#" className="text-secondary me-3 d-inline-block">
                <FontAwesomeIcon icon={faUser} className="fa-solid me-2" />
                <span
                  className="d-none d-md-inline ms-1"
                  onClick={() => openModal("loginform")}
                >
                  Đăng nhập
                </span>
              </a>
              <a href="#" className="text-secondary me-3 d-inline-block">
                <FontAwesomeIcon icon={faBell} className="fa-solid me-2" />
              </a>
              {/* <a href="#" className="text-secondary d-inline-block">
                <FontAwesomeIcon
                  icon={faCartShopping}
                  className="fa-solid me-1"
                />
                <span className="d-none d-md-inline ms-1">Giỏ hàng</span>
              </a> */}
              <OffCanvasExample
                name="Giỏ hàng"
                title="Giỏ hàng"
                buttonClassName="text-secondary d-inline-block"
                icon={
                  <FontAwesomeIcon
                    icon={faCartShopping}
                    className="fa-solid me-1"
                  />
                }
              >
                <CartPage />
              </OffCanvasExample>
            </div>
          </div>
        </div>
      </div>

      {/* Modals của lib react bootstrap*/}
      <Modal show={show && modalType === "loginform"} onHide={closeModal}>
        <Modal.Header closeButton>
          <Modal.Title>Login Form</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Login />
        </Modal.Body>
      </Modal>
    </>
  );
};

export default Header;
