/**** nội dung page này thiết kết page cart giỏ hàng** */

"use client";
import Image from "next/image";

const CartPage = () => {
  return (
    <>
      <div className="d-flex flex-column section1-hero-flex mb-3">
        <div className="d-flex align-items-center justify-content-between w-100 mb-1">
          <nav className="breadcrumb mb-0 bg-transparent p-0 small">
            <a className="text-primary" href="#">
              Trang chủ&nbsp;
            </a>
            <span className="breadcrumb-item active text-secondary">
              / Laptop
            </span>
            <span className="breadcrumb-item active"> cart</span>
          </nav>
        </div>
      </div>

      <section className="cart-section py-4">
        <div className="container">
          <div className="cart-box bg-white rounded shadow-sm p-4">
            <div className="d-flex align-items-center mb-3">
              <input
                type="checkbox"
                className="htmlForm-check-input me-2"
                id="cartSelectAll"
                checked
              />
              <label htmlFor="cartSelectAll" className="fw-bold">
                Chọn tất cả (1)
              </label>
            </div>

            <div className="cart-item-box mb-3 p-3 rounded-3 bg-light position-relative">
              <div className="d-flex align-items-center mb-2">
                <input
                  type="checkbox"
                  className="htmlForm-check-input me-2 cart-item-check"
                  checked
                />
                <Image
                  src="/assets/client/img/anhtemp/e-2.jpg"
                  alt="Laptop"
                  className="cart-item-Image rounded me-3"
                  style={{ objectFit: "cover" }}
                  width={60}
                  height={60}
                />
                <div className="flex-grow-1">
                  <div className="fw-bold cart-item-title">
                    Laptop Dell Inspiron N3530 i5 1334U/16GB/512GB/15.6
                    FHD/Win11/Office HS21
                  </div>
                  <div className="small text-muted">Màu: Bạc</div>
                </div>
                <div className="text-end ms-2">
                  <div className="cart-item-price text-danger fw-bold">
                    17.990.000 đh
                  </div>
                  <div className="cart-item-oldprice text-muted small">
                    <del>19.990.000 đ</del>
                  </div>
                </div>
                <button className="btn btn-link text-danger ms-2 cart-item-remove">
                  <i className="fa fa-trash"></i>
                </button>
              </div>
              <div className="d-flex align-items-center gap-2 mb-2">
                <span className="fw-bold">Số lượng:</span>
                <div
                  className="input-group input-group-sm cart-qty-group"
                  style={{ width: "110px" }}
                >
                  <button
                    className="btn btn-outline-secondary cart-qty-minus"
                    type="button"
                  >
                    -
                  </button>
                  <input
                    type="text"
                    className="htmlForm-control text-center cart-qty-input"
                    value="1"
                    style={{ maxWidth: "40px" }}
                  />
                  <button
                    className="btn btn-outline-secondary cart-qty-plus"
                    type="button"
                  >
                    +
                  </button>
                </div>
              </div>
              <div className="cart-warranty-box bg-white rounded-3 p-3 mt-2">
                <div className="fw-bold mb-2">
                  <i className="fa fa-shield-alt text-danger me-2"></i>Chọn gói
                  bảo hành
                </div>
                <div className="htmlForm-check mb-1">
                  <input
                    className="htmlForm-check-input"
                    type="checkbox"
                    id="warranty1"
                  />
                  <label className="htmlForm-check-label" htmlFor="warranty1">
                    Đặc quyền Bảo hành thêm 1 năm MTXT (BT)
                    <span className="text-danger fw-bold">+600.000 đ</span>
                    <span className="text-muted small">
                      <del>1.000.000 đ</del>
                    </span>
                  </label>
                </div>
                <div className="htmlForm-check">
                  <input
                    className="htmlForm-check-input"
                    type="checkbox"
                    id="warranty2"
                  />
                  <label className="htmlForm-check-label" htmlFor="warranty2">
                    Đặc quyền Bảo hành trọn đời
                    <span className="text-danger fw-bold">+1.500.000 đ</span>
                    <span className="text-muted small">
                      <del>2.400.000 đ</del>
                    </span>
                  </label>
                </div>
              </div>
            </div>

            <div className="cart-summary-box bg-light rounded-3 p-3 mb-2">
              <div className="mb-2">
                <span className="fw-bold">Chọn hoặc nhập ưu đãi</span>
                <input
                  type="text"
                  className="htmlForm-control htmlForm-control-sm mt-1"
                  placeholder="Nhập mã ưu đãi hoặc chọn..."
                />
              </div>
              <div className="mb-2">
                <span className="fw-bold">Đổi 0 điểm (~0đ)</span>
              </div>
              <div className="mb-2">
                <span className="fw-bold">Thông tin đơn hàng</span>
                <div className="d-flex justify-content-between mt-1">
                  <span>Tổng tiền</span>
                  <span className="fw-bold">19.990.000 đ</span>
                </div>
                <hr className="my-2" />
                <div className="d-flex justify-content-between mt-1">
                  <span>Tổng khuyến mãi</span>
                  <span className="text-success">2.000.000 đ</span>
                </div>
                <hr className="my-2" />
                <div className="d-flex justify-content-between mt-1">
                  <span>Cần thanh toán</span>
                  <span className="text-danger fw-bold">17.990.000 đ</span>
                </div>
                <hr className="my-2" />
                <div className="d-flex justify-content-between mt-1">
                  <span>Điểm thưởng</span>
                  <span>0</span>
                </div>
              </div>
              <div className="text-end">
                <button className="btn btn-danger btn-lg px-5 cart-confirm-btn">
                  Xác nhận đơn
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};
export default CartPage;
