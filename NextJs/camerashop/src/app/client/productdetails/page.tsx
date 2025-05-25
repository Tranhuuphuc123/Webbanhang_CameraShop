/**đây là phần thiế kế page product details** */

"use client";
import Image from "next/image";

const ProductDetails = () => {
  return (
    <>
      {/* SESSION 1: nav logo */}
      <div className="d-flex flex-column section1-hero-flex mb-3">
        <div className="d-flex align-items-center justify-content-between w-100 mb-1">
          <nav className="breadcrumb mb-0 bg-transparent p-0 small">
            <a className="text-primary" href="#">
              Trang chủ&nbsp;
            </a>
            <span className="breadcrumb-item active text-secondary">
              / Laptop
            </span>
            <span className="breadcrumb-item active">Tên sản phẩm</span>
          </nav>
        </div>
      </div>
      {/* END SESSION 1: nav logo */}

      {/* SESSION 2: sản phẩm chi tiết */}
      <section className="product-detail-section py-4">
        <div className="container">
          <div className="row g-4 align-items-start">
            {/* Gallery bên trái */}
            <div className="col-lg-8 mb-3 mb-lg-0">
              <div className="product-gallery">
                <div className="product-gallery-main mb-3 position-relative">
                  <button
                    className="btn btn-light gallery-nav gallery-prev position-absolute top-50 start-0 translate-middle-y"
                    type="button"
                  >
                    <i className="fa fa-chevron-left"></i>
                  </button>
                  <Image
                    id="mainProductImg"
                    src="/assets/client/img/anhtemp/e-1.jpg"
                    alt="Ảnh sản phẩm lớn"
                    className="img-fluid rounded shadow-sm gallery-main-img"
                    width={500}
                    height={350}
                  />
                  <button
                    className="btn btn-light gallery-nav gallery-next position-absolute top-50 end-0 translate-middle-y"
                    type="button"
                  >
                    <i className="fa fa-chevron-right"></i>
                  </button>
                </div>
                <div className="d-flex justify-content-center gap-2 product-gallery-thumbs mb-3">
                  <Image
                    src="/assets/client/img/anhtemp/e-1.jpg"
                    className="img-thumbnail gallery-thumb active"
                    data-img="/assets/client/img/anhtemp/e-1.jpg"
                    alt="Thumb 1"
                    width={80}
                    height={60}
                  />
                  <Image
                    src="/assets/client/img/anhtemp/e-2.jpg"
                    className="img-thumbnail gallery-thumb"
                    data-img="/assets/client/img/anhtemp/e-2.jpg"
                    alt="Thumb 2"
                    width={80}
                    height={60}
                  />
                  <Image
                    src="/assets/client/img/anhtemp/e-3.jpg"
                    className="img-thumbnail gallery-thumb"
                    data-img="/assets/client/img/anhtemp/e-3.jpg"
                    alt="Thumb 3"
                    width={80}
                    height={60}
                  />
                  <Image
                    src="/assets/client/img/anhtemp/e-4.jpg"
                    className="img-thumbnail gallery-thumb"
                    data-img="/assets/client/img/anhtemp/e-4.jpg"
                    alt="Thumb 4"
                    width={80}
                    height={60}
                  />
                </div>
                {/* Thông số nổi bật & Chính sách sản phẩm */}
                <div className="product-feature-box p-3 mb-2 bg-white rounded shadow-sm">
                  <div className="d-flex justify-content-between align-items-center mb-2 flex-wrap gap-2">
                    <div className="fw-bold fs-6 mb-0">Thông số nổi bật</div>
                    <a href="#" className="btn btn-outline-danger btn-sm px-3">
                      Xem tất cả thông số
                    </a>
                  </div>
                  <div className="row g-2 mb-2">
                    <div className="col-12 col-md-4">
                      <div className="d-flex align-items-center gap-2 p-2 border rounded bg-light h-100">
                        <i className="fa-solid fa-microchip fa-lg text-secondary"></i>
                        <div>
                          <div className="small text-muted">CPU</div>
                          <div className="fw-bold">Core i5</div>
                        </div>
                      </div>
                    </div>
                    <div className="col-12 col-md-4">
                      <div className="d-flex align-items-center gap-2 p-2 border rounded bg-light h-100">
                        <i className="fa-brands fa-amazon fa-lg text-secondary"></i>
                        <div>
                          <div className="small text-muted">Card đồ hoạ</div>
                          <div className="fw-bold">Intel Iris Xe Graphics</div>
                        </div>
                      </div>
                    </div>
                    <div className="col-12 col-md-4">
                      <div className="d-flex align-items-center gap-2 p-2 border rounded bg-light h-100">
                        <i className="fa-solid fa-display fa-lg text-secondary"></i>
                        <div>
                          <div className="small text-muted">
                            Kích thước màn hình
                          </div>
                          <div className="fw-bold">15.6</div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <hr className="my-2" />
                  <div className="d-flex justify-content-between align-items-center mb-2 flex-wrap gap-2">
                    <div className="fw-bold fs-6 mb-0">Chính sách sản phẩm</div>
                    <a href="#" className="small text-primary">
                      Tìm hiểu thêm
                    </a>
                  </div>
                  <div className="row g-2">
                    <div className="col-6 col-md-3 d-flex align-items-center gap-2">
                      <i className="fa-solid fa-certificate text-danger"></i>
                      <span className="small">
                        Hàng chính hãng - Bảo hành 12 tháng
                      </span>
                    </div>
                    <div className="col-6 col-md-3 d-flex align-items-center gap-2">
                      <i className="fa-solid fa-truck-fast text-danger"></i>
                      <span className="small">
                        Giao hàng miễn phí toàn quốc
                      </span>
                    </div>
                    <div className="col-6 col-md-3 d-flex align-items-center gap-2">
                      <i className="fa-solid fa-screwdriver-wrench text-danger"></i>
                      <span className="small">Hỗ trợ cài đặt miễn phí</span>
                    </div>
                    <div className="col-6 col-md-3 d-flex align-items-center gap-2">
                      <i className="fa-solid fa-headset text-danger"></i>
                      <span className="small">
                        Kỹ thuật viên hỗ trợ trực tuyến
                      </span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            {/* Thông tin & đặt hàng bên phải */}
            <div className="col-lg-4">
              <div className="product-info-box p-4 rounded shadow-sm bg-white">
                <h2 className="product-title mb-2">
                  Laptop Dell Inspiron 15 3520 i5 1235U/16GB/512GB/15.6&quot;FHD
                </h2>
                <div className="mb-2">
                  <span className="badge bg-success me-2">Còn hàng</span>
                  <span className="text-warning">
                    <i className="fa fa-star"></i> 4.8
                  </span>
                  <span className="text-muted ms-2 small">(120 đánh giá)</span>
                </div>
                <div className="product-price-box mb-3">
                  <span className="product-oldprice">17.290.000 đ</span>
                  <span className="product-price ms-2">16.490.000 đ</span>
                  <span className="product-discount ms-2">-5%</span>
                </div>
                <div className="mb-3">
                  <div className="text-success fw-bold mb-1">
                    Ưu đãi & Quà tặng
                  </div>
                  <ul className="list-unstyled mb-0">
                    <li>
                      <i className="fa fa-gift text-danger me-2"></i>Tặng Office
                      Home 2024 bản quyền
                    </li>
                    <li>
                      <i className="fa fa-gift text-danger me-2"></i>Độ bền
                      chuẩn quân đội
                    </li>
                    <li>
                      <i className="fa fa-gift text-danger me-2"></i>Giảm thêm
                      800.000đ khi thanh toán VNPAY
                    </li>
                  </ul>
                </div>
                <div className="mb-3">
                  <div className="fw-bold mb-1">Chọn cấu hình:</div>
                  <select className="form-select w-auto d-inline-block mb-2">
                    <option>16GB/512GB</option>
                    <option>8GB/256GB</option>
                    <option>32GB/1TB</option>
                  </select>
                </div>
                <div className="mb-3">
                  <button className="btn btn-danger btn-lg px-2 me-2">
                    Mua ngay
                  </button>
                  <button className="btn btn-outline-danger btn-lg px-2">
                    Thêm vào giỏ
                  </button>
                </div>
                <div className="small text-muted">
                  Giao hàng tận nơi toàn quốc. Đổi trả trong 7 ngày nếu lỗi nhà
                  sản xuất.
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END SESSION 2: sản phẩm chi tiết */}

      {/* SESSION 3: mô tả sản phẩm */}
      <section className="product-desc-section py-3">
        <div className="container">
          <div className="product-desc-box bg-white rounded shadow-sm p-4">
            <h3 className="desc-title mb-3">Mô tả sản phẩm</h3>
            <div className="desc-content" id="descContent">
              <p>
                <a href="#" className="text-primary fw-bold">
                  Dell Inspiron 15 3530
                </a>{" "}
                là một lựa chọn tốt trong phân khúc laptop tầm{" "}
                <b>20 triệu đồng</b>. Màn hình siêu ấn tượng với tần số quét
                120Hz tái hiện chuyển động mượt mà. Cấu hình phần cứng với chip{" "}
                <b>Intel Core i5 1334U</b> cùng 16GB RAM và ổ cứng SSD 512GB đáp
                ứng tốt nhu cầu xử lý công việc.
              </p>
              <p className="fw-bold mb-1">
                Hiệu suất ổn định cho công việc văn phòng
              </p>
              <p>
                Laptop Dell Inspiron 3530 được trang bị bộ vi xử lý{" "}
                <b>Intel Core i5 1334U</b> xây dựng trên tiến trình 10nm, gồm 10
                nhân 12 luồng. Trong đó có 2 P-Core và 8 E-Core, tốc độ xung
                nhịp tối đa của chipset này đạt tới 4.6GHz. Công nghệ Turbo
                Boost Max 3.0 và Intel Thread Director tối ưu hiệu suất lên mức
                tốt nhất để đáp ứng các tác vụ nặng như chỉnh sửa ảnh, biên tập
                video,... Các hoạt động thông thường như duyệt web, chạy ứng
                dụng văn phòng hay xem YouTube, lướt mạng xã hội sẽ diễn ra mượt
                mà, không xuất hiện giật lag.
              </p>
              <div className="desc-img-wrap text-center my-3">
                <Image
                  src="/assets/images/product-img/e-2.jpg"
                  alt="Mô tả sản phẩm"
                  className="desc-img img-fluid rounded shadow"
                  width={500}
                  height={350}
                />
              </div>
              <div className="desc-more collapse" id="descMore">
                <p>
                  Với thiết kế mỏng nhẹ, Dell Inspiron 15 3530 dễ dàng mang theo
                  bên mình. Bàn phím fullsize, touchpad rộng rãi, pin dung lượng
                  lớn giúp bạn làm việc cả ngày dài. Hệ điều hành Windows 11 bản
                  quyền, bảo mật vân tay, webcam HD, loa stereo sống động,...
                  tất cả đều góp phần tạo nên trải nghiệm tuyệt vời cho người
                  dùng.
                </p>
                <p>
                  Máy còn hỗ trợ nhiều cổng kết nối hiện đại như USB-C, HDMI,
                  khe thẻ nhớ SD, WiFi 6, Bluetooth 5.2,... giúp kết nối thiết
                  bị ngoại vi dễ dàng. Sản phẩm phù hợp cho học sinh, sinh viên,
                  nhân viên văn phòng, người làm việc di động.
                </p>
              </div>
            </div>
            <div className="text-center mt-2">
              <button
                className="btn btn-outline-secondary btn-sm px-4"
                id="descToggleBtn"
                data-bs-toggle="collapse"
                data-bs-target="#descMore"
                aria-expanded="false"
                aria-controls="descMore"
              >
                Đọc thêm
              </button>
            </div>
          </div>
        </div>
      </section>
      {/*END SESSION 3: mô tả sản phẩm */}

      {/* SESSION 4: phần bình luận */}
      <section className="product-review-section py-3">
        <div className="container">
          <div className="product-review-box bg-white rounded shadow-sm p-4">
            <div className="row g-3 align-items-center">
              <div className="col-md-3 text-center">
                <div className="review-score display-4 fw-bold">4.2</div>
                <div className="small text-muted">6 lượt đánh giá</div>
                <div className="mb-2">
                  <span className="text-warning fs-4">
                    &#9733;&#9733;&#9733;&#9733;&#9734;
                  </span>
                </div>
                <button className="btn btn-danger btn-sm px-4 mb-2">
                  Đánh giá sản phẩm
                </button>
              </div>
              <div className="col-md-5">
                <div className="review-bar-list">
                  <div className="d-flex align-items-center mb-1">
                    <span className="me-1 small">5</span>
                    <span className="text-warning me-1">&#9733;</span>
                    <div
                      className="progress flex-grow-1 me-2"
                      style={{ height: 8 }}
                    >
                      <div
                        className="progress-bar bg-danger"
                        style={{ width: "80%" }}
                      ></div>
                    </div>
                    <span className="small">4</span>
                  </div>
                  <div className="d-flex align-items-center mb-1">
                    <span className="me-1 small">4</span>
                    <span className="text-warning me-1">&#9733;</span>
                    <div
                      className="progress flex-grow-1 me-2"
                      style={{ height: 8 }}
                    >
                      <div
                        className="progress-bar bg-danger"
                        style={{ width: "30%" }}
                      ></div>
                    </div>
                    <span className="small">1</span>
                  </div>
                  <div className="d-flex align-items-center mb-1">
                    <span className="me-1 small">3</span>
                    <span className="text-warning me-1">&#9733;</span>
                    <div
                      className="progress flex-grow-1 me-2"
                      style={{ height: 8 }}
                    >
                      <div
                        className="progress-bar bg-danger"
                        style={{ width: "0%" }}
                      ></div>
                    </div>
                    <span className="small">0</span>
                  </div>
                  <div className="d-flex align-items-center mb-1">
                    <span className="me-1 small">2</span>
                    <span className="text-warning me-1">&#9733;</span>
                    <div
                      className="progress flex-grow-1 me-2"
                      style={{ height: 8 }}
                    >
                      <div
                        className="progress-bar bg-danger"
                        style={{ width: "0%" }}
                      ></div>
                    </div>
                    <span className="small">0</span>
                  </div>
                  <div className="d-flex align-items-center mb-1">
                    <span className="me-1 small">1</span>
                    <span className="text-warning me-1">&#9733;</span>
                    <div
                      className="progress flex-grow-1 me-2"
                      style={{ height: 8 }}
                    >
                      <div
                        className="progress-bar bg-danger"
                        style={{ width: "20%" }}
                      ></div>
                    </div>
                    <span className="small">1</span>
                  </div>
                </div>
              </div>
              <div className="col-md-4 text-md-end text-center mt-3 mt-md-0">
                <div className="review-filter-group d-inline-flex flex-wrap gap-2">
                  <button className="btn btn-outline-danger btn-sm active">
                    Tất cả
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    5 <span className="text-warning">&#9733;</span>
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    4 <span className="text-warning">&#9733;</span>
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    3 <span className="text-warning">&#9733;</span>
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    2 <span className="text-warning">&#9733;</span>
                  </button>
                  <button className="btn btn-outline-secondary btn-sm">
                    1 <span className="text-warning">&#9733;</span>
                  </button>
                </div>
              </div>
            </div>
            <hr className="my-3" />
            <div className="mb-2 fw-bold">19 Bình luận</div>
            <form className="review-form mb-3">
              <div className="mb-2">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Nhập nội dung bình luận..."
                  maxLength={3000}
                />
              </div>
              <div className="d-flex align-items-center gap-2 mb-2">
                <span className="text-primary small">
                  <i className="fa fa-image"></i> Thêm tối đa 5 ảnh và 1 video
                </span>
              </div>
              <div className="d-flex align-items-center gap-2">
                <button type="submit" className="btn btn-dark btn-sm px-4">
                  Gửi bình luận
                </button>
              </div>
            </form>
            <div className="review-list">
              <div className="review-item d-flex align-items-start mb-3">
                <div
                  className="review-avatar rounded-circle bg-secondary text-white d-flex align-items-center justify-content-center me-3"
                  style={{ width: 40, height: 40 }}
                >
                  M
                </div>
                <div className="flex-grow-1">
                  <div className="d-flex align-items-center gap-2 mb-1">
                    <span className="fw-bold">Mạnh</span>
                    <span className="text-muted small">• 5 tháng trước</span>
                  </div>
                  <div className="review-content mb-1">
                    Máy đáp ứng được nhu cầu chơi game cơ bản không?
                  </div>
                  <div className="d-flex align-items-center gap-2">
                    <button className="btn btn-light btn-sm px-2 py-0">
                      <i className="fa fa-thumbs-up"></i> 0
                    </button>
                    <button className="btn btn-light btn-sm px-2 py-0">
                      Trả lời
                    </button>
                  </div>
                </div>
              </div>
              {/* Thêm các review-item khác nếu muốn */}
            </div>
          </div>
        </div>
      </section>
      {/* END SESSION 4: phần bình luận */}

      {/* SESSION 5: - câu hỏi thường gặp */}
      <section className="product-faq-section py-3">
        <div className="container">
          <div className="product-faq-box bg-white rounded shadow-sm p-4">
            <h3 className="faq-title mb-3">Câu hỏi thường gặp</h3>
            <div className="accordion product-faq-accordion" id="faqAccordion">
              <div className="accordion-item mb-2">
                <h2 className="accordion-header" id="faqHeading1">
                  <button
                    className="accordion-button collapsed d-flex justify-content-between align-items-center"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#faqCollapse1"
                    aria-expanded="false"
                    aria-controls="faqCollapse1"
                  >
                    Laptop có cần card đồ họa rời không?
                    <span className="faq-toggle-icon ms-auto"></span>
                  </button>
                </h2>
                <div
                  id="faqCollapse1"
                  className="accordion-collapse collapse"
                  aria-labelledby="faqHeading1"
                  data-bs-parent="#faqAccordion"
                >
                  <div className="accordion-body">
                    Nếu bạn chơi game, dựng video hoặc làm đồ họa 3D thì cần
                    card rời. Nếu chỉ dùng văn phòng, học tập thì card tích hợp
                    đã đủ dùng.
                  </div>
                </div>
              </div>
              <div className="accordion-item mb-2">
                <h2 className="accordion-header" id="faqHeading2">
                  <button
                    className="accordion-button collapsed d-flex justify-content-between align-items-center"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#faqCollapse2"
                    aria-expanded="false"
                    aria-controls="faqCollapse2"
                  >
                    Dùng laptop có cần cài phần mềm diệt virus không?
                    <span className="faq-toggle-icon ms-auto"></span>
                  </button>
                </h2>
                <div
                  id="faqCollapse2"
                  className="accordion-collapse collapse"
                  aria-labelledby="faqHeading2"
                  data-bs-parent="#faqAccordion"
                >
                  <div className="accordion-body">
                    Nếu bạn thường xuyên tải file, truy cập web lạ thì nên cài
                    phần mềm diệt virus để bảo vệ dữ liệu.
                  </div>
                </div>
              </div>
              <div className="accordion-item mb-2">
                <h2 className="accordion-header" id="faqHeading3">
                  <button
                    className="accordion-button collapsed d-flex justify-content-between align-items-center"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#faqCollapse3"
                    aria-expanded="false"
                    aria-controls="faqCollapse3"
                  >
                    Bảo quản laptop như thế nào cho đúng cách?
                    <span className="faq-toggle-icon ms-auto"></span>
                  </button>
                </h2>
                <div
                  id="faqCollapse3"
                  className="accordion-collapse collapse"
                  aria-labelledby="faqHeading3"
                  data-bs-parent="#faqAccordion"
                >
                  <div className="accordion-body">
                    Để laptop nơi khô ráo, tránh nước, vệ sinh định kỳ, không để
                    vật nặng lên máy, sử dụng túi chống sốc khi di chuyển.
                  </div>
                </div>
              </div>
              <div className="accordion-item mb-2">
                <h2 className="accordion-header" id="faqHeading4">
                  <button
                    className="accordion-button collapsed d-flex justify-content-between align-items-center"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#faqCollapse4"
                    aria-expanded="false"
                    aria-controls="faqCollapse4"
                  >
                    Laptop có dùng chung sạc được không?
                    <span className="faq-toggle-icon ms-auto"></span>
                  </button>
                </h2>
                <div
                  id="faqCollapse4"
                  className="accordion-collapse collapse"
                  aria-labelledby="faqHeading4"
                  data-bs-parent="#faqAccordion"
                >
                  <div className="accordion-body">
                    Nên dùng đúng sạc theo máy, nếu dùng sạc khác phải đúng điện
                    áp, dòng điện và chân cắm tương thích.
                  </div>
                </div>
              </div>
              <div className="accordion-item mb-2">
                <h2 className="accordion-header" id="faqHeading5">
                  <button
                    className="accordion-button collapsed d-flex justify-content-between align-items-center"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#faqCollapse5"
                    aria-expanded="false"
                    aria-controls="faqCollapse5"
                  >
                    Có thể nâng cấp RAM hoặc ổ cứng sau khi mua không?
                    <span className="faq-toggle-icon ms-auto"></span>
                  </button>
                </h2>
                <div
                  id="faqCollapse5"
                  className="accordion-collapse collapse"
                  aria-labelledby="faqHeading5"
                  data-bs-parent="#faqAccordion"
                >
                  <div className="accordion-body">
                    Đa số laptop hiện nay đều có thể nâng cấp RAM hoặc ổ cứng,
                    nhưng nên kiểm tra kỹ thông số trước khi mua.
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* ENDSESSION 5: - câu hỏi thường gặp */}

      {/* SESSION 6:  logo quảng cáo chất lượng */}
      <section className="quality-ads-section py-3">
        <div className="container-fluid bg-light rounded-4 py-3">
          <div className="row justify-content-center text-center g-4 quality-ads-row">
            <div className="col-6 col-md-3">
              <div className="quality-ads-item">
                <div className="quality-ads-icon mb-2 mx-auto">
                  <i className="fa-solid fa-shield-check"></i>
                </div>
                <div className="fw-bold">Thương hiệu đảm bảo</div>
                <div className="small">Nhập khẩu, bảo hành chính hãng</div>
              </div>
            </div>
            <div className="col-6 col-md-3">
              <div className="quality-ads-item">
                <div className="quality-ads-icon mb-2 mx-auto">
                  <i className="fa-solid fa-truck"></i>
                </div>
                <div className="fw-bold">Đổi trả dễ dàng</div>
                <div className="small">
                  Theo chính sách đổi trả tại FPT Shop
                </div>
              </div>
            </div>
            <div className="col-6 col-md-3">
              <div className="quality-ads-item">
                <div className="quality-ads-icon mb-2 mx-auto">
                  <i className="fa-solid fa-arrows-rotate"></i>
                </div>
                <div className="fw-bold">Sản phẩm chất lượng</div>
                <div className="small">Đảm bảo tương thích và độ bền cao</div>
              </div>
            </div>
            <div className="col-6 col-md-3">
              <div className="quality-ads-item">
                <div className="quality-ads-icon mb-2 mx-auto">
                  <i className="fa-solid fa-location-dot"></i>
                </div>
                <div className="fw-bold">Giao hàng tận nơi</div>
                <div className="small">Tại 63 tỉnh thành</div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END SECTION 6:  logo quảng cáo chất lượng */}
    </>
  );
};
export default ProductDetails;
