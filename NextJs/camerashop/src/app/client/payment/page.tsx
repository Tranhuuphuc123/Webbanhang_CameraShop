"use client";
import Image from "next/image";

/**page thanh toán**/

const Payment = () => {
  return (
    <>
      {/* session1: nav logo */}
      <div className="d-flex flex-column section1-hero-flex mb-3">
        <div className="d-flex align-items-center justify-content-between w-100 mb-1">
          <nav className="breadcrumb mb-0 bg-transparent p-0 small">
            <a className="text-primary" href="#">
              Trang chủ&nbsp;
            </a>
            <span className="breadcrumb-item active text-secondary">
              / Laptop
            </span>
            <span className="breadcrumb-item active"> Thanh toán</span>
          </nav>
        </div>
      </div>
      {/* END session1: nav logo */}

      {/* SECTION 2 thanh toán  */}
      <section className="checkout-section py-4">
        <div className="container">
          <div className="row g-4">
            {/* LEFT: Thông tin đơn hàng */}
            <div className="col-lg-7 col-12">
              <div className="checkout-box bg-white rounded shadow-sm p-4 mb-4">
                {/* Sản phẩm trong đơn */}
                <div className="checkout-product-box mb-3">
                  <div className="d-flex align-items-center mb-2">
                    <a href="#" className="text-primary small">
                      <i className="fa fa-arrow-left me-1"></i> Quay lại giỏ
                      hàng
                    </a>
                  </div>
                  <div className="d-flex align-items-center mb-2">
                    <Image
                      src="/assets/client/img/anhtemp/e-2.jpg"
                      alt="Laptop"
                      className="rounded me-3"
                      style={{ width: 56, height: 56, objectFit: "cover" }}
                      width={56}
                      height={56}
                    />
                    <div className="flex-grow-1">
                      <div className="fw-bold">
                        Laptop Dell Inspiron N3530 i5
                        1334U/16GB/512GB/15.6&quot;FHD/Win11/Office HS21
                      </div>
                      <div className="small text-muted">Màu: Bạc</div>
                    </div>
                    <div className="text-end ms-2">
                      <div className="text-danger fw-bold">17.990.000 đ</div>
                      <div className="text-muted small">
                        <del>19.990.000 đ</del>
                      </div>
                    </div>
                  </div>
                  <div className="d-flex align-items-center gap-2 mb-2">
                    <button className="btn btn-outline-secondary btn-sm">
                      <i className="fa fa-gift me-1"></i> 4 Quà tặng đơn hàng
                    </button>
                    <span className="badge bg-warning text-dark small">
                      Vui lòng đăng nhập để đổi điểm
                    </span>
                  </div>
                </div>
                {/* Nhập thông tin người đặt hàng */}
                <div className="checkout-user-box mb-3">
                  <div className="fw-bold mb-2">Người đặt hàng</div>
                  <input
                    type="text"
                    className="form-control mb-2"
                    placeholder="Họ và tên"
                  />
                  <input
                    type="text"
                    className="form-control mb-2"
                    placeholder="Số điện thoại"
                  />
                  <input
                    type="email"
                    className="form-control"
                    placeholder="Email (không bắt buộc)"
                  />
                </div>
                {/* Hình thức nhận hàng */}
                <div className="checkout-delivery-box mb-3">
                  <div className="fw-bold mb-2">Hình thức nhận hàng</div>
                  <div className="form-check form-check-inline">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="deliveryType"
                      id="deliveryHome"
                      defaultChecked
                    />
                    <label className="form-check-label" htmlFor="deliveryHome">
                      Giao hàng tận nơi
                    </label>
                  </div>
                  <div className="form-check form-check-inline">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="deliveryType"
                      id="deliveryStore"
                    />
                    <label className="form-check-label" htmlFor="deliveryStore">
                      Nhận tại cửa hàng
                    </label>
                  </div>
                  <input
                    type="text"
                    className="form-control mt-2"
                    placeholder="Tỉnh/Thành Phố, Quận/Huyện, Phường/Xã"
                  />
                  <textarea
                    className="form-control mt-2"
                    rows={2}
                    maxLength={128}
                    placeholder="Ghi chú (Ví dụ: Hãy gọi tôi khi chuẩn bị hàng xong)"
                  ></textarea>
                  <div className="form-check mt-2">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="otherReceiver"
                    />
                    <label className="form-check-label" htmlFor="otherReceiver">
                      Nhờ người khác nhận hàng
                    </label>
                  </div>
                  <div className="form-check mt-1">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="needSupport"
                    />
                    <label className="form-check-label" htmlFor="needSupport">
                      Yêu cầu hỗ trợ kỹ thuật
                    </label>
                  </div>
                </div>
                {/* Xuất hóa đơn điện tử */}
                <div className="checkout-invoice-box mb-3 d-flex align-items-center justify-content-between">
                  <span className="fw-bold">Xuất hóa đơn điện tử</span>
                  <div className="form-check form-switch mb-0">
                    <input
                      className="form-check-input"
                      type="checkbox"
                      id="invoiceSwitch"
                    />
                  </div>
                </div>
                {/* Phương thức thanh toán */}
                <div className="checkout-payment-box mb-2">
                  <div className="fw-bold mb-2">Phương thức thanh toán</div>
                  <div className="payment-method-list">
                    {/* Demo: chỉ liệt kê, bạn sẽ code logic sau */}
                    <div className="form-check mb-2">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="paymentMethod"
                        id="payCash"
                        defaultChecked
                      />
                      <label
                        className="form-check-label d-flex align-items-center"
                        htmlFor="payCash"
                      >
                        <Image
                          src="/assets/images/payment/cash.svg"
                          alt="cash"
                          className="me-2"
                          width={24}
                          height={24}
                        />{" "}
                        Thanh toán khi nhận hàng
                      </label>
                    </div>
                    <div className="form-check mb-2">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="paymentMethod"
                        id="payQR"
                      />
                      <label
                        className="form-check-label d-flex align-items-center"
                        htmlFor="payQR"
                      >
                        <Image
                          src="/assets/images/payment/qr.svg"
                          alt="qr"
                          className="me-2"
                          width={24}
                          height={24}
                        />{" "}
                        Thanh toán bằng QR Code, thẻ ATM nội địa
                      </label>
                    </div>
                    <div className="form-check mb-2">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="paymentMethod"
                        id="payVisa"
                      />
                      <label
                        className="form-check-label d-flex align-items-center"
                        htmlFor="payVisa"
                      >
                        <Image
                          src="/assets/images/payment/visa.svg"
                          alt="visa"
                          className="me-2"
                          width={24}
                          height={24}
                        />{" "}
                        Thanh toán quốc tế Visa, Master, JCB, AMEX, Apple Pay,
                        Google Pay
                      </label>
                    </div>
                    {/* ... Thêm các phương thức khác tương tự ... */}
                  </div>
                </div>
              </div>
            </div>
            {/* RIGHT: Để bạn tự code */}
            <div className="col-lg-5 col-12">
              <div className="checkout-right-box bg-white rounded shadow-sm p-4 h-100">
                <div className="order-summary-box">
                  <div className="fw-bold mb-3 fs-5">Thông tin đơn hàng</div>
                  <div className="order-summary-list">
                    <div className="d-flex justify-content-between align-items-center py-1">
                      <span>Tổng tiền</span>
                      <span className="fw-bold">19.990.000 đ</span>
                    </div>
                    <div className="d-flex justify-content-between align-items-center py-1">
                      <span>Tổng khuyến mãi</span>
                      <span className="fw-bold text-success">2.000.000 đ</span>
                    </div>
                    <div className="d-flex justify-content-between align-items-center py-1">
                      <span>Phí vận chuyển</span>
                      <span className="text-primary">Miễn phí</span>
                    </div>
                    <div className="d-flex justify-content-between align-items-center py-1">
                      <span>Cần thanh toán</span>
                      <span className="fw-bold text-danger fs-5">
                        17.990.000 đ
                      </span>
                    </div>
                    <div className="d-flex justify-content-between align-items-center py-1">
                      <span>Điểm thưởng</span>
                      <span className="text-warning fw-bold">
                        <i className="fa fa-coins me-1"></i>+4,497
                      </span>
                    </div>
                  </div>
                  <div className="order-summary-detail mb-2 mt-2">
                    <a href="#" className="text-primary small">
                      Xem chi tiết <i className="fa fa-chevron-down ms-1"></i>
                    </a>
                  </div>
                  <button className="btn btn-danger btn-lg w-100 mb-3 order-btn">
                    Đặt hàng
                  </button>
                  <div className="order-summary-note text-center small text-muted">
                    Bằng việc tiến hành đặt mua hàng, bạn đồng ý với
                    <br />
                    <a href="#" className="fw-bold text-decoration-underline">
                      Điều khoản dịch vụ
                    </a>{" "}
                    và{" "}
                    <a href="#" className="fw-bold text-decoration-underline">
                      Chính sách xử lý dữ liệu cá nhân
                    </a>
                    <br />
                    của FPT Shop
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END SECTION 2 thanh toán  */}
    </>
  );
};

export default Payment;
