/**page index.tsx này xử lý lib offcanvas của lib react bootstrap
 * ==> đó lib tạo form xuất hiện từ trong ra ngoài theo các hướng
 * top: trên
 * bottom: dưới
 * left: trái
 * right: phải
 * => có thể tham khảo thêm ở lib react bootstrap: OffCanvas
 *
 */

"use client";
import { useState } from "react";
import Button from "react-bootstrap/Button";
import Offcanvas from "react-bootstrap/Offcanvas";

interface OffCanvasExampleProps {
  name: string;
  title?: string;
  children?: React.ReactNode;
  buttonClassName?: string;
  icon?: React.ReactNode;
}

export default function OffCanvasExample({
  name,
  title,
  children,
  buttonClassName,
  icon,
}: OffCanvasExampleProps) {
  //khai báo state show để xử lý hiển thị form
  const [show, setShow] = useState(false);

  //hàm xử lý đóng form
  const handleClose = () => setShow(false);

  //hàm xử lý hiển thị form
  const handleShow = () => setShow(true);

  //trả về form
  return (
    <>
      {/* <!-- Button --> */}
      <Button
        variant="link"
        onClick={handleShow}
        className={buttonClassName}
        style={{boxShadow: "none" }}
      >
        {icon}
        <span className="d-none d-md-inline ms-1">{name}</span>
      </Button>
      <Offcanvas
        show={show}
        onHide={handleClose}
        placement="end"
        style={{ maxWidth: "600px", width: "100vw", zIndex: 2000 }}
      >
        <Offcanvas.Header closeButton>
          <Offcanvas.Title
            style={{ width: "100%", textAlign: "center" }}
            className="fs-3"
          >
            {title || name}
          </Offcanvas.Title>
        </Offcanvas.Header>
        <hr
          className="m-0"
          style={{ backgroundColor: "#dee2e6", opacity: 0.8 }}
        />
        <Offcanvas.Body>{children}</Offcanvas.Body>
      </Offcanvas>
    </>
  );
}
