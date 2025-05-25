/***đây là page của trang product***/

"use client";
import Image from "next/image";

//import lib swiper js dùng trong react/nextjs
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";

const ProductsPage = () => {
  return (
    <>
      {/* ****PHẦN NAV LOGO */}
      <div className="d-flex flex-column section1-hero-flex mb-3">
        <div className="d-flex align-items-center justify-content-between w-100 mb-1">
          <nav className="breadcrumb mb-0 bg-transparent p-0 small">
            <a className="text-primary" href="#">
              Trang chủ&nbsp;
            </a>
            <span className="breadcrumb-item active">/ Laptop</span>
          </nav>
        </div>

        <div className="d-flex align-items-center justify-content-between w-100 mt-5">
          {/* <!-- Bên trái: Nội dung --> */}
          <div className="d-flex flex-column justify-content-center flex-grow-1">
            <h1 className="section1-title text-primary mb-2">Laptop</h1>
            <div className="d-flex align-items-center gap-2 flex-wrap">
              <span className="section1-desc">Tìm sản phẩm theo nhu cầu</span>
              <button className="btn btn-outline-secondary d-flex align-items-center section1-filter-btn">
                <i className="fa fa-filter me-2"></i> Dùng bộ lọc ngay
              </button>
            </div>
          </div>
          {/* <!-- Bên phải: Ảnh minh họa --> */}
          <div className="ms-3 d-none d-md-flex align-items-center justify-content-end section1-illustration">
            <Image
              src="/assets/client/img/anhtemp/img-1.jpg"
              alt="Laptop Illustration"
              className="section1-img-hero"
              width={100}
              height={100}
            />
          </div>
        </div>
      </div>
      {/* ****END PHẦN NAV LOGO */}

      {/* PHẦN LIB SWIPPER JS */}
      <div className="mt-5">
        <h4 className="fw-bold mb-3" style={{ color: "#222" }}>
          CÁC NHÃN HÀNG
        </h4>
        <Swiper
          modules={[Pagination]}
          slidesPerView={"auto"}
          spaceBetween={16}
          grabCursor={true}
          pagination={{ clickable: true }}
          className="mySwiper"
          style={{ width: "100%", paddingTop: 10, paddingBottom: 10 }}
        >
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/acer.webp"
              alt="Acer"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/asus.webp"
              alt="Asus"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/dell.webp"
              alt="Dell"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/hp.webp"
              alt="HP"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/lenovo.webp"
              alt="Lenovo"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/msi.webp"
              alt="MSI"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/acer.webp"
              alt="Acer"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/asus.webp"
              alt="Asus"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/dell.webp"
              alt="Dell"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/hp.webp"
              alt="HP"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
          <SwiperSlide>
            <Image
              src="/assets/client/img/anhtemp/lenovo.webp"
              alt="Lenovo"
              width={150}
              height={60}
              style={{ width: "100%", height: 60, objectFit: "contain" }}
              unoptimized
            />
          </SwiperSlide>
        </Swiper>
        <style jsx global>{`
          .swiper {
            width: 100%;
            padding-top: 10px;
            padding-bottom: 10px;
          }
          :global(.swiper-slide) {
            width: 150px !important;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: transparent !important;
            box-shadow: none !important;
          }
          :global(.swiper-slide img) {
            display: block;
            width: 100%;
          }
          :global(.swiper-slide:hover) {
            border: 2px solid #d31515c4;
            border-radius: 10px;
          }
          .swiper {
            padding-bottom: 10px;
          }
        `}</style>
      </div>
      {/* END PHẦN LIB SWIPPER JS */}

      {/* PHẦN QUẢNG CÁO */}
      <section id="deal">
        <div className="wrapper">
          <div className="container">
            <div className="row">
              <div className="col-12">
                <div className="text-center border border-white deal-of-the-day py-5">
                  <div className="text-content py-5">
                    <h2>DEALS OF THE DAY</h2>
                    <h4>UPTO 69% OFF</h4>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                      Etiam et purus a odio finibus bibendum in sit amet leo.
                      Mauris feugiat erat tellus.
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END PHẦN QUẢNG CÁO */}

      {/* DANH MỤC CÁC LOẠI SẢN PHẢM */}
      <section className="product-category-list my-4">
        <div className="container">
          <div className="row justify-content-center g-4">
            <div className="col-6 col-sm-4 col-md-2 me-4">
              <div className="category-card text-center">
                <Image
                  src="/assets/client/img/anhtemp/img-1.jpg"
                  alt="AI"
                  className="category-icon mb-2"
                  width={100}
                  height={100}
                />
                <div className="category-title">AI</div>
              </div>
            </div>
            <div className="col-6 col-sm-4 col-md-2 me-4">
              <div className="category-card text-center">
                <Image
                  src="/assets/client/img/anhtemp/img-2.jpg"
                  alt="Gaming - Đồ họa"
                  className="category-icon mb-2"
                  width={100}
                  height={100}
                />
                <div className="category-title">Gaming - Đồ họa</div>
              </div>
            </div>
            <div className="col-6 col-sm-4 col-md-2 me-4">
              <div className="category-card text-center">
                <Image
                  src="/assets/client/img/anhtemp/img-3.jpg"
                  alt="Sinh viên - Văn phòng"
                  className="category-icon mb-2"
                  width={100}
                  height={100}
                />
                <div className="category-title">Sinh viên - Văn phòng</div>
              </div>
            </div>
            <div className="col-6 col-sm-4 col-md-2 me-4">
              <div className="category-card text-center">
                <Image
                  src="/assets/client/img/anhtemp/img-4.jpg"
                  alt="Mỏng nhẹ"
                  className="category-icon mb-2"
                  width={100}
                  height={100}
                />
                <div className="category-title">Mỏng nhẹ</div>
              </div>
            </div>
            <div className="col-6 col-sm-4 col-md-2 me-4">
              <div className="category-card text-center">
                <Image
                  src="/assets/client/img/anhtemp/b-1.jpg"
                  alt="Doanh nhân"
                  className="category-icon mb-2"
                  width={100}
                  height={100}
                />
                <div className="category-title">Doanh nhân</div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* END DANH MỤC CÁC LOẠI SẢN PHẢM */}

      {/* LIST PRODUCT VÀ LỌC TIỀM KIẾM */}
      <section className="product-listing-section my-4">
        <div className="container-fluid">
          <div className="row product-listing-grid">
            {/* Bộ lọc tìm kiếm (Sidebar) */}
            <aside className="col-lg-3 col-md-4 mb-3 product-filter-col">
              <div className="product-filter-box p-3">
                <h5 className="fw-bold mb-3">
                  <i className="fa fa-filter me-2"></i>Bộ lọc tìm kiếm
                </h5>
                <div className="mb-3">
                  <div className="fw-bold mb-2">Hãng sản xuất</div>
                  <div className="d-flex flex-wrap gap-2 mb-2">
                    <button className="btn btn-outline-secondary btn-sm">
                      Asus
                    </button>
                    <button className="btn btn-outline-secondary btn-sm">
                      Apple
                    </button>
                    <button className="btn btn-outline-secondary btn-sm">
                      Lenovo
                    </button>
                    <button className="btn btn-outline-secondary btn-sm">
                      Acer
                    </button>
                    <button className="btn btn-outline-secondary btn-sm">
                      MSI
                    </button>
                    <button className="btn btn-outline-secondary btn-sm">
                      Dell
                    </button>
                  </div>
                  <a href="#" className="small text-primary">
                    Xem thêm
                  </a>
                </div>
                <div className="mb-3">
                  <div className="fw-bold mb-2">Mức giá</div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="allPrice"
                      defaultChecked
                    />
                    <label className="form-check-label" htmlFor="allPrice">
                      Tất cả
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price1"
                    />
                    <label className="form-check-label" htmlFor="price1">
                      Dưới 10 triệu
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price2"
                    />
                    <label className="form-check-label" htmlFor="price2">
                      Từ 10 - 15 triệu
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price3"
                    />
                    <label className="form-check-label" htmlFor="price3">
                      Từ 15 - 20 triệu
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price4"
                    />
                    <label className="form-check-label" htmlFor="price4">
                      Từ 20 - 25 triệu
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price5"
                    />
                    <label className="form-check-label" htmlFor="price5">
                      Từ 25 - 30 triệu
                    </label>
                  </div>
                  <div className="form-check mb-1">
                    <input
                      className="form-check-input"
                      type="radio"
                      name="price"
                      id="price6"
                    />
                    <label className="form-check-label" htmlFor="price6">
                      Trên 30 triệu
                    </label>
                  </div>
                  <div className="mt-2">
                    <div className="small mb-1">
                      Hoặc nhập khoảng giá phù hợp với bạn:
                    </div>
                    <div className="d-flex align-items-center gap-2">
                      <input
                        type="number"
                        className="form-control form-control-sm"
                        placeholder="Từ"
                        style={{ maxWidth: "90px" }}
                      />
                      <span>~</span>
                      <input
                        type="number"
                        className="form-control form-control-sm"
                        placeholder="Đến"
                        style={{ maxWidth: "90px" }}
                      />
                    </div>
                  </div>
                </div>
              </div>
            </aside>

            {/* Danh sách sản phẩm (Main) */}
            <main className="col-lg-9 col-md-8 product-list-col">
              {/* tiêu đề products */}
              <div className="d-flex justify-content-between align-items-center mb-3 flex-wrap gap-2">
                <div className="fw-bold">
                  Tìm thấy <span className="text-primary">366</span> kết quả
                </div>
                <div className="d-flex align-items-center gap-2">
                  <span className="me-1">Sắp xếp theo:</span>
                  <select
                    className="form-select form-select-sm"
                    style={{ width: "auto" }}
                  >
                    <option>Nổi bật</option>
                    <option>Giá thấp đến cao</option>
                    <option>Giá cao đến thấp</option>
                    <option>Mới nhất</option>
                  </select>
                </div>
              </div>

              {/* danh sách sản phẩm */}
              <div className="row g-3 product-card-list">
                {/* Card sản phẩm mẫu 1*/}
                <div className="col-12 col-sm-6 col-md-4 col-lg-3">
                  {/* card 1 */}
                  <div className="product-card h-100 me-3">
                    <div className="product-card-img-wrap">
                      <Image
                        src="/assets/client/img/anhtemp/img-1.jpg"
                        alt="Laptop 1"
                        className="product-card-img"
                        width={300}
                        height={200}
                      />
                      <div className="product-card-badge">Trả góp 0%</div>
                      <div className="product-card-sale">-5%</div>
                    </div>
                    <div className="product-card-body">
                      <div className="product-card-title">
                        Laptop Dell Inspiron 15 3520 i5
                        1235U/16GB/512GB/15.6&quot;FHD
                      </div>
                      <div className="product-card-price-row">
                        <span className="product-card-oldprice">
                          17.290.000 đ
                        </span>
                        <span className="product-card-price">16.490.000 đ</span>
                      </div>
                      <div className="product-card-desc">Giảm 800.000 đ</div>
                      <div className="product-card-note">
                        Tặng Office Home 2024, Độ bền chuẩn quân đội
                      </div>
                      <div className="product-card-payments mt-2">
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/4/41/Visa_Logo.png"
                          alt="Visa"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/0/04/Mastercard-logo.png"
                          alt="MasterCard"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png"
                          alt="Momo"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Logo_VNPAY.png"
                          alt="VNPAY"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                      </div>
                      <div className="mt-2">
                        <a href="#" className="small text-primary">
                          So sánh
                        </a>
                      </div>
                    </div>
                  </div>

                  {/* card 2 */}
                  <div className="product-card h-100 me-3">
                    <div className="product-card-img-wrap">
                      <Image
                        src="/assets/client/img/anhtemp/img-2.jpg"
                        alt="Laptop 1"
                        className="product-card-img"
                        width={300}
                        height={200}
                      />
                      <div className="product-card-badge">Trả góp 0%</div>
                      <div className="product-card-sale">-5%</div>
                    </div>
                    <div className="product-card-body">
                      <div className="product-card-title">
                        Laptop Dell Inspiron 15 3520 i5
                        1235U/16GB/512GB/15.6&quot;FHD
                      </div>
                      <div className="product-card-price-row">
                        <span className="product-card-oldprice">
                          17.290.000 đ
                        </span>
                        <span className="product-card-price">16.490.000 đ</span>
                      </div>
                      <div className="product-card-desc">Giảm 800.000 đ</div>
                      <div className="product-card-note">
                        Tặng Office Home 2024, Độ bền chuẩn quân đội
                      </div>
                      <div className="product-card-payments mt-2">
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/4/41/Visa_Logo.png"
                          alt="Visa"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/0/04/Mastercard-logo.png"
                          alt="MasterCard"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png"
                          alt="Momo"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Logo_VNPAY.png"
                          alt="VNPAY"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                      </div>
                      <div className="mt-2">
                        <a href="#" className="small text-primary">
                          So sánh
                        </a>
                      </div>
                    </div>
                  </div>

                  {/* card 3 */}
                  <div className="product-card h-100 me-3">
                    <div className="product-card-img-wrap">
                      <Image
                        src="/assets/client/img/anhtemp/img-3.jpg"
                        alt="Laptop 1"
                        className="product-card-img"
                        width={300}
                        height={200}
                      />
                      <div className="product-card-badge">Trả góp 0%</div>
                      <div className="product-card-sale">-5%</div>
                    </div>
                    <div className="product-card-body">
                      <div className="product-card-title">
                        Laptop Dell Inspiron 15 3520 i5
                        1235U/16GB/512GB/15.6&quot;FHD
                      </div>
                      <div className="product-card-price-row">
                        <span className="product-card-oldprice">
                          17.290.000 đ
                        </span>
                        <span className="product-card-price">16.490.000 đ</span>
                      </div>
                      <div className="product-card-desc">Giảm 800.000 đ</div>
                      <div className="product-card-note">
                        Tặng Office Home 2024, Độ bền chuẩn quân đội
                      </div>
                      <div className="product-card-payments mt-2">
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/4/41/Visa_Logo.png"
                          alt="Visa"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/0/04/Mastercard-logo.png"
                          alt="MasterCard"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png"
                          alt="Momo"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Logo_VNPAY.png"
                          alt="VNPAY"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                      </div>
                      <div className="mt-2">
                        <a href="#" className="small text-primary">
                          So sánh
                        </a>
                      </div>
                    </div>
                  </div>

                  {/* card 4 */}
                  <div className="product-card h-100 me-3">
                    <div className="product-card-img-wrap">
                      <Image
                        src="/assets/client/img/anhtemp/img-4.jpg"
                        alt="Laptop 1"
                        className="product-card-img"
                        width={300}
                        height={200}
                      />
                      <div className="product-card-badge">Trả góp 0%</div>
                      <div className="product-card-sale">-5%</div>
                    </div>
                    <div className="product-card-body">
                      <div className="product-card-title">
                        Laptop Dell Inspiron 15 3520 i5
                        1235U/16GB/512GB/15.6&quot;FHD
                      </div>
                      <div className="product-card-price-row">
                        <span className="product-card-oldprice">
                          17.290.000 đ
                        </span>
                        <span className="product-card-price">16.490.000 đ</span>
                      </div>
                      <div className="product-card-desc">Giảm 800.000 đ</div>
                      <div className="product-card-note">
                        Tặng Office Home 2024, Độ bền chuẩn quân đội
                      </div>
                      <div className="product-card-payments mt-2">
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/4/41/Visa_Logo.png"
                          alt="Visa"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/0/04/Mastercard-logo.png"
                          alt="MasterCard"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/vi/f/fe/MoMo_Logo.png"
                          alt="Momo"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                        <Image
                          src="https://upload.wikimedia.org/wikipedia/commons/2/2a/Logo_VNPAY.png"
                          alt="VNPAY"
                          width={28}
                          height={18}
                          style={{ maxWidth: "28px" }}
                        />
                      </div>
                      <div className="mt-2">
                        <a href="#" className="small text-primary">
                          So sánh
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </main>
          </div>
        </div>
      </section>
      {/* END LIST PRODUCT VÀ LỌC TIỀM KIẾM */}
    </>
  );
};
export default ProductsPage;
