"use client";
import Image from "next/image";

//import lib swiper js dùng trong react/nextjs
import { Swiper, SwiperSlide } from "swiper/react";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";

//import lib carousel của react bootstrap
import Carousel from "react-bootstrap/Carousel";
import ExampleCarouselImage from "@/components/ExampleCarouselImage";

//import tabs ui của lib react bootstrap
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";

export default function Home() {
  return (
    <>
      {/* <!--***************SESSION 1: top banner: sử dụng lib carousel của react bootstrap *************--> */}
      <Carousel>
        <Carousel.Item>
          <ExampleCarouselImage text="First slide" />
          <Carousel.Caption>
            <h3>First slide label</h3>
            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <ExampleCarouselImage text="Second slide" />
          <Carousel.Caption>
            <h3>Second slide label</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
          </Carousel.Caption>
        </Carousel.Item>
        <Carousel.Item>
          <ExampleCarouselImage text="Third slide" />
          <Carousel.Caption>
            <h3>Third slide label</h3>
            <p>
              Praesent commodo cursus magna, vel scelerisque nisl consectetur.
            </p>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
      {/* <!--******************* END SESSION 1: top banner *********************--> */}

      {/* <!-- ******************** END SECTION 2 ƯU ĐÃI HOT ******************** --> */}
      <section id="hot-offers">
        <div className="container py-3">
          <div className="row mb-3">
            <div className="col-12">
              <h2 className="fw-bold fs-4 text-success">ƯU ĐÃI HOT</h2>
            </div>
          </div>
          <div className="hot-offer-row">
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/1828/1828919.png"
                  alt="Tải app"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">TẢI APP PHONG VŨ</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/992/992700.png"
                  alt="Giờ vàng"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">GIỜ VÀNG GIÁ SỐC</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                  alt="Laptop AI"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">LAPTOP AI</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/190/190411.png"
                  alt="HSSV"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">ƯU ĐÃI HỌC SINH SINH VIÊN</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/1828/1828961.png"
                  alt="Giảm tới"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">ĐỒ GIA DỤNG</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/5968/5968267.png"
                  alt="ASUS Fest"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">ASUS FEST</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/1828/1828970.png"
                  alt="Trả góp"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">TRẢ GÓP 0%</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/1828/1828911.png"
                  alt="Apple"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">APPLE EDUCATION</div>
              </div>
            </div>
            <div className="hot-offer-col">
              <div className="hot-offer-item">
                <Image
                  src="https://cdn-icons-png.flaticon.com/512/1828/1828917.png"
                  alt="Freeship"
                  className="hot-offer-icon"
                  width={100}
                  height={100}
                />
                <div className="hot-offer-title">FREESHIP</div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- ******************** END SECTION 2 ƯU ĐÃI HOT ******************** --> */}

      {/* <!-- ******************** SECTION 3 -  SWIPPER JS QUẢNG CÁO ******************** --> */}
      <div className="mt-5">
        <h4 className="fw-bold mb-3" style={{ color: "#222" }}>
          TIN CÔNG NGHỆ
        </h4>
        <Swiper
          modules={[Pagination]}
          slidesPerView={"auto"}
          spaceBetween={16}
          grabCursor={true}
          pagination={{ clickable: true }}
          className="mySwiper"
          style={{ paddingBottom: 10, paddingTop: 10, width: "100%" }}
        >
          {/* Card 1 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-1.jpg"
                alt="Tin công nghệ 1"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 2 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-2.jpg"
                alt="Tin công nghệ 2"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 3 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-3.jpg"
                alt="Tin công nghệ 3"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 4 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-4.jpg"
                alt="Tin công nghệ 4"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 5 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-5.jpg"
                alt="Tin công nghệ 5"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 6 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-6.jpg"
                alt="Tin công nghệ 6"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 7 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-7.jpg"
                alt="Tin công nghệ 7"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>

          {/* Card 8 */}
          <SwiperSlide
            style={{
              width: 320,
              height: 200,
              display: "flex",
              alignItems: "stretch",
              justifyContent: "center",
              background: "none",
            }}
          >
            <div className="news-card">
              <Image
                src="https://swiperjs.com/demos/images/nature-8.jpg"
                alt="Tin công nghệ 8"
                width={320}
                height={150}
                style={{
                  width: "100%",
                  height: 150,
                  objectFit: "cover",
                  flexShrink: 0,
                }}
                unoptimized
              />
              <p className="news-title">
                Xóa ngay 12 ứng dụng Android độc hại...
              </p>
            </div>
          </SwiperSlide>
        </Swiper>
      </div>

      {/* cách viết css trong react/nextjs: phần này bổ sung riêng cho lib swiperjs */}
      <style jsx>{`
        .news-card {
          background: #fff;
          box-shadow: 0 2px 8px rgba(0, 0, 0, 0.07);
          overflow: hidden;
          width: 100%;
          height: 100%;
          display: flex;
          flex-direction: column;
        }
        .news-title {
          font-size: 1rem;
          color: #222;
          font-weight: 500;
          padding: 10px 10px 0 10px;
          overflow: hidden;
          display: -webkit-box;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          white-space: normal;
          text-overflow: ellipsis;
          min-height: 2.6em;
          background: #fff;
        }
      `}</style>
      {/* <!-- ******************** END SECTION 3 -  SWIPPER JS QUẢNG CÁO ******************** --> */}

      {/* <!-- ******************** SECTION 4 -  (ĐỀ MỤC PRODUCT - DÙNG TABS UI JS)******************** --> */}
      <section className="my-4">
        <div className="container">
          {/* tên đề mục */}
          <div className="pv-flashsale-header d-flex justify-content-between align-items-end mb-2">
            <div className="pv-section-title text-primary fw-bold">
              <i className="fa-solid fa-bolt me-2"></i>SẢN PHẨM NỔI BẬT
            </div>
          </div>

          {/* chứa tabs ui */}
          <div className="p-4">
            {/* đoạn này là lib Tabs của react bootstrap hé */}
            <Tabs
              defaultActiveKey="profile"
              id="fill-tab-example"
              className="mb-3 pv-flashsale-tabS"
              fill
            >
              {/* Tab 1 */}
              <Tab
                eventKey="home"
                title="Home"
                className="pv-flashsale-tab-content"
              >
                {/* ********* Tab PC ********* */}
                <div
                  className="tab-pane fade show active"
                  id="pane-pc"
                  role="tabpanel"
                  aria-labelledby="tab-pc"
                >
                  <div className="row align-items-stretch">
                    {/* Bên trái: Quảng cáo Flash Sale */}
                    <div className="col-lg-4 col-md-5 mb-3 mb-md-0 d-flex flex-column justify-content-center align-items-center">
                      <div className="pv-flashsale-ads text-center p-3 mt-5">
                        <div className="pv-flashsale-ads-timer mt-5 mb-2">
                          <span className="pv-flashsale-ads-label">
                            Kết thúc sau
                          </span>
                          <div className="pv-flashsale-ads-clock d-flex justify-content-center gap-2 mt-1">
                            <div className="pv-flashsale-ads-box">02</div>
                            <div className="pv-flashsale-ads-box">12</div>
                            <div className="pv-flashsale-ads-box">34</div>
                            <div className="pv-flashsale-ads-box">56</div>
                          </div>
                        </div>
                        <div className="pv-flashsale-ads-note mt-2">
                          <span className="badge bg-info text-dark">
                            *Sản phẩm và số lượng có hạn
                          </span>
                        </div>
                      </div>
                    </div>
                    {/* Bên phải: Card sản phẩm */}
                    <div className="col-lg-8 col-md-7">
                      <div className="row ">
                        {/* card 1 */}
                        <div className="col-xl-4 col-lg-6 col-md-12 col-12">
                          <div className="pv-pc-card">
                            <div className="pv-pc-img-wrap">
                              <Image
                                src="/assets/client/img/anhtemp/b-2.jpg"
                                alt="PC 1"
                                className="pv-pc-img"
                                width={100}
                                height={100}
                              />
                              <div className="pv-pc-badge">
                                TIẾT KIỆM
                                <br />
                                2.609.000 ₫
                              </div>
                            </div>
                            <div className="pv-pc-info">
                              <div className="pv-pc-brand">PHONG VŨ</div>
                              <div className="pv-pc-title">
                                PC PV Home Office I50168 (Intel Core i5-12400F/
                                2 x 8GB/ 500GB SSD/ Free DOS)
                              </div>
                              <div className="pv-pc-combo">
                                COMBO GIẢM ~ 3.258.000 ₫
                              </div>
                              <div className="pv-pc-price-row">
                                <span className="pv-pc-price">8.840.000 ₫</span>
                                <span className="pv-pc-oldprice">
                                  11.449.000 ₫
                                </span>
                                <span className="pv-pc-discount">-22,79%</span>
                              </div>
                              <div className="pv-pc-gift">
                                <Image
                                  src="/assets/client/img/anhtemp/gift1.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift2.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift3.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                              </div>
                              <button className="btn pv-pc-btn">
                                Thêm vào giỏ
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Tab>

              {/* Tab 2 */}
              <Tab
                eventKey="profile"
                title="Profile"
                className="pv-flashsale-tab-content"
              >
                {/* ********* Tab PC ********* */}
                <div
                  className="tab-pane fade show active"
                  id="pane-pc"
                  role="tabpanel"
                  aria-labelledby="tab-pc"
                >
                  <div className="row align-items-stretch">
                    {/* Bên trái: Quảng cáo Flash Sale */}
                    <div className="col-lg-4 col-md-5 mb-3 mb-md-0 d-flex flex-column justify-content-center align-items-center">
                      <div className="pv-flashsale-ads text-center p-3 mt-5">
                        <div className="pv-flashsale-ads-timer mt-5 mb-2">
                          <span className="pv-flashsale-ads-label">
                            Kết thúc sau
                          </span>
                          <div className="pv-flashsale-ads-clock d-flex justify-content-center gap-2 mt-1">
                            <div className="pv-flashsale-ads-box">02</div>
                            <div className="pv-flashsale-ads-box">12</div>
                            <div className="pv-flashsale-ads-box">34</div>
                            <div className="pv-flashsale-ads-box">56</div>
                          </div>
                        </div>
                        <div className="pv-flashsale-ads-note mt-2">
                          <span className="badge bg-info text-dark">
                            *Sản phẩm và số lượng có hạn
                          </span>
                        </div>
                      </div>
                    </div>
                    {/* Bên phải: Card sản phẩm */}
                    <div className="col-lg-8 col-md-7">
                      <div className="row ">
                        {/* card 1 */}
                        <div className="col-xl-4 col-lg-6 col-md-12 col-12">
                          <div className="pv-pc-card">
                            <div className="pv-pc-img-wrap">
                              <Image
                                src="/assets/client/img/anhtemp/b-3.jpg"
                                alt="PC 1"
                                className="pv-pc-img"
                                width={100}
                                height={100}
                              />
                              <div className="pv-pc-badge">
                                TIẾT KIỆM
                                <br />
                                2.609.000 ₫
                              </div>
                            </div>
                            <div className="pv-pc-info">
                              <div className="pv-pc-brand">PHONG VŨ</div>
                              <div className="pv-pc-title">
                                PC PV Home Office I50168 (Intel Core i5-12400F/
                                2 x 8GB/ 500GB SSD/ Free DOS)
                              </div>
                              <div className="pv-pc-combo">
                                COMBO GIẢM ~ 3.258.000 ₫
                              </div>
                              <div className="pv-pc-price-row">
                                <span className="pv-pc-price">8.840.000 ₫</span>
                                <span className="pv-pc-oldprice">
                                  11.449.000 ₫
                                </span>
                                <span className="pv-pc-discount">-22,79%</span>
                              </div>
                              <div className="pv-pc-gift">
                                <Image
                                  src="/assets/client/img/anhtemp/gift1.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift2.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift3.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                              </div>
                              <button className="btn pv-pc-btn">
                                Thêm vào giỏ
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Tab>

              {/* Tabs 3 */}
              <Tab
                eventKey="longer-tab"
                title="Loooonger Tab"
                className="pv-flashsale-tab-content"
              >
                {/* ********* Tab PC ********* */}
                <div
                  className="tab-pane fade show active"
                  id="pane-pc"
                  role="tabpanel"
                  aria-labelledby="tab-pc"
                >
                  <div className="row align-items-stretch">
                    {/* Bên trái: Quảng cáo Flash Sale */}
                    <div className="col-lg-4 col-md-5 mb-3 mb-md-0 d-flex flex-column justify-content-center align-items-center">
                      <div className="pv-flashsale-ads text-center p-3 mt-5">
                        <div className="pv-flashsale-ads-timer mt-5 mb-2">
                          <span className="pv-flashsale-ads-label">
                            Kết thúc sau
                          </span>
                          <div className="pv-flashsale-ads-clock d-flex justify-content-center gap-2 mt-1">
                            <div className="pv-flashsale-ads-box">02</div>
                            <div className="pv-flashsale-ads-box">12</div>
                            <div className="pv-flashsale-ads-box">34</div>
                            <div className="pv-flashsale-ads-box">56</div>
                          </div>
                        </div>
                        <div className="pv-flashsale-ads-note mt-2">
                          <span className="badge bg-info text-dark">
                            *Sản phẩm và số lượng có hạn
                          </span>
                        </div>
                      </div>
                    </div>
                    {/* Bên phải: Card sản phẩm */}
                    <div className="col-lg-8 col-md-7">
                      <div className="row ">
                        {/* card 1 */}
                        <div className="col-xl-4 col-lg-6 col-md-12 col-12">
                          <div className="pv-pc-card">
                            <div className="pv-pc-img-wrap">
                              <Image
                                src="/assets/client/img/anhtemp/b-1.jpg"
                                alt="PC 1"
                                className="pv-pc-img"
                                width={100}
                                height={100}
                              />
                              <div className="pv-pc-badge">
                                TIẾT KIỆM
                                <br />
                                2.609.000 ₫
                              </div>
                            </div>
                            <div className="pv-pc-info">
                              <div className="pv-pc-brand">PHONG VŨ</div>
                              <div className="pv-pc-title">
                                PC PV Home Office I50168 (Intel Core i5-12400F/
                                2 x 8GB/ 500GB SSD/ Free DOS)
                              </div>
                              <div className="pv-pc-combo">
                                COMBO GIẢM ~ 3.258.000 ₫
                              </div>
                              <div className="pv-pc-price-row">
                                <span className="pv-pc-price">8.840.000 ₫</span>
                                <span className="pv-pc-oldprice">
                                  11.449.000 ₫
                                </span>
                                <span className="pv-pc-discount">-22,79%</span>
                              </div>
                              <div className="pv-pc-gift">
                                <Image
                                  src="/assets/client/img/anhtemp/gift1.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift2.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                                <Image
                                  src="/assets/client/img/anhtemp/gift3.png"
                                  alt=""
                                  width={100}
                                  height={100}
                                />
                              </div>
                              <button className="btn pv-pc-btn">
                                Thêm vào giỏ
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Tab>

              {/* Tab 4 */}
              <Tab eventKey="contact" title="" disabled>
                Tab content for Contact
              </Tab>
            </Tabs>
          </div>
        </div>
      </section>
      {/* <!-- ******************** END SECTION 4 -  (ĐỀ MỤC PRODUCT - DÙNG TABS UI JS)******************** --> */}

      {/* <!-- ******************** SECTION 5 -  DANH MỤC NỔI BẬT******************** --> */}
      <section id="featured-categories" className="my-4">
        <div className="container bg-white rounded shadow-sm py-3 px-2">
          <div className="d-flex align-items-center mb-3">
            <h5 className="fw-bold mb-0" style={{ color: "#222" }}>
              Danh mục nổi bật
            </h5>
          </div>
          <div className="d-flex flex-wrap justify-content-start gap-3 gap-md-4">
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041916.png"
                  alt="Laptop"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Laptop</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/747/747376.png"
                  alt="Apple"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Apple</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041913.png"
                  alt="PC"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">PC</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041917.png"
                  alt="Linh kiện"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Linh Kiện</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041915.png"
                  alt="Màn hình"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Màn Hình</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041922.png"
                  alt="Phụ kiện"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Phụ Kiện</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041920.png"
                  alt="Thiết bị mạng"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Thiết Bị Mạng</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041921.png"
                  alt="An ninh"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Thiết Bị An Ninh</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041918.png"
                  alt="Văn phòng"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Thiết Bị Văn Phòng</div>
            </div>
            <div className="cat-feature-box text-center">
              <div className="cat-feature-icon mb-2">
                <Image
                  src="https://cdn-icons-png.flaticon.com/128/1041/1041923.png"
                  alt="Điện máy"
                  width={100}
                  height={100}
                />
              </div>
              <div className="cat-feature-title">Điện Máy - Điện Gia Dụng</div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- ******************** END SECTION 5 -  DANH MỤC NỔI BẬT******************** --> */}

      {/* <!-- ******************** SECTION 6 -  QUẢNG CÁO******************** --> */}
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
      {/* <!-- ******************** END SECTION 6 -  QUẢNG CÁO******************** --> */}

      {/* <!-- ******************** SECTION 7-  contain product pages mini******************** --> */}
      <section className="pv-section-pc my-4">
        <div className="container pv-pc-container">
          {/* header contain này */}
          <div className="pv-pc-header d-flex justify-content-between align-items-end">
            {/* tiêu đề mục */}
            <div className="pv-section-title text-white fw-bold">
              <i className="fa-solid fa-desktop me-2"></i>PC
            </div>
            <a href="#" className="pv-section-link text-white fw-bold">
              Xem tất cả <i className="fa-solid fa-angle-right"></i>
            </a>
          </div>

          {/* underline ngăn cách */}
          <div className="pv-pc-underline"></div>

          {/* row chứa các card */}
          <div className="row g-2 mt-2">
            {/* Card 1 */}
            <div className="col-xl-3 col-lg-4 col-md-6 col-12">
              <div className="pv-pc-card">
                <div className="pv-pc-img-wrap">
                  <Image
                    src="/assets/client/img/anhtemp/b-1.jpg"
                    alt="PC 1"
                    className="pv-pc-img"
                    width={100}
                    height={100}
                  />
                  <div className="pv-pc-badge">
                    TIẾT KIỆM
                    <br />
                    2.609.000 ₫
                  </div>
                </div>
                <div className="pv-pc-info">
                  <div className="pv-pc-brand">PHONG VŨ</div>
                  <div className="pv-pc-title">
                    PC PV Home Office I50168 (Intel Core i5-12400F/ 2 x 8GB/
                    500GB SSD/ Free DOS)
                  </div>
                  <div className="pv-pc-combo">COMBO GIẢM ~ 3.258.000 ₫</div>
                  <div className="pv-pc-price-row">
                    <span className="pv-pc-price">8.840.000 ₫</span>
                    <span className="pv-pc-oldprice">11.449.000 ₫</span>
                    <span className="pv-pc-discount">-22,79%</span>
                  </div>
                  <div className="pv-pc-gift">
                    <Image
                      src="/assets/client/img/anhtemp/gift1.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift2.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift3.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                  </div>
                  <button className="btn pv-pc-btn">Thêm vào giỏ</button>
                </div>
              </div>
            </div>

            {/* Card 2 */}
            <div className="col-xl-3 col-lg-4 col-md-6 col-12">
              <div className="pv-pc-card">
                <div className="pv-pc-img-wrap">
                  <Image
                    src="/assets/client/img/anhtemp/b-1.jpg"
                    alt="PC 1"
                    className="pv-pc-img"
                    width={100}
                    height={100}
                  />
                  <div className="pv-pc-badge">
                    TIẾT KIỆM
                    <br />
                    2.609.000 ₫
                  </div>
                </div>
                <div className="pv-pc-info">
                  <div className="pv-pc-brand">PHONG VŨ</div>
                  <div className="pv-pc-title">
                    PC PV Home Office I50168 (Intel Core i5-12400F/ 2 x 8GB/
                    500GB SSD/ Free DOS)
                  </div>
                  <div className="pv-pc-combo">COMBO GIẢM ~ 3.258.000 ₫</div>
                  <div className="pv-pc-price-row">
                    <span className="pv-pc-price">8.840.000 ₫</span>
                    <span className="pv-pc-oldprice">11.449.000 ₫</span>
                    <span className="pv-pc-discount">-22,79%</span>
                  </div>
                  <div className="pv-pc-gift">
                    <Image
                      src="/assets/client/img/anhtemp/gift1.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift2.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift3.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                  </div>
                  <button className="btn pv-pc-btn">Thêm vào giỏ</button>
                </div>
              </div>
            </div>

            {/* Card 3 */}
            <div className="col-xl-3 col-lg-4 col-md-6 col-12">
              <div className="pv-pc-card">
                <div className="pv-pc-img-wrap">
                  <Image
                    src="/assets/client/img/anhtemp/b-1.jpg"
                    alt="PC 1"
                    className="pv-pc-img"
                    width={100}
                    height={100}
                  />
                  <div className="pv-pc-badge">
                    TIẾT KIỆM
                    <br />
                    2.609.000 ₫
                  </div>
                </div>
                <div className="pv-pc-info">
                  <div className="pv-pc-brand">PHONG VŨ</div>
                  <div className="pv-pc-title">
                    PC PV Home Office I50168 (Intel Core i5-12400F/ 2 x 8GB/
                    500GB SSD/ Free DOS)
                  </div>
                  <div className="pv-pc-combo">COMBO GIẢM ~ 3.258.000 ₫</div>
                  <div className="pv-pc-price-row">
                    <span className="pv-pc-price">8.840.000 ₫</span>
                    <span className="pv-pc-oldprice">11.449.000 ₫</span>
                    <span className="pv-pc-discount">-22,79%</span>
                  </div>
                  <div className="pv-pc-gift">
                    <Image
                      src="/assets/client/img/anhtemp/gift1.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift2.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift3.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                  </div>
                  <button className="btn pv-pc-btn">Thêm vào giỏ</button>
                </div>
              </div>
            </div>

            {/* Card 4 */}
            <div className="col-xl-3 col-lg-4 col-md-6 col-12">
              <div className="pv-pc-card">
                <div className="pv-pc-img-wrap">
                  <Image
                    src="/assets/client/img/anhtemp/b-1.jpg"
                    alt="PC 1"
                    className="pv-pc-img"
                    width={100}
                    height={100}
                  />
                  <div className="pv-pc-badge">
                    TIẾT KIỆM
                    <br />
                    2.609.000 ₫
                  </div>
                </div>
                <div className="pv-pc-info">
                  <div className="pv-pc-brand">PHONG VŨ</div>
                  <div className="pv-pc-title">
                    PC PV Home Office I50168 (Intel Core i5-12400F/ 2 x 8GB/
                    500GB SSD/ Free DOS)
                  </div>
                  <div className="pv-pc-combo">COMBO GIẢM ~ 3.258.000 ₫</div>
                  <div className="pv-pc-price-row">
                    <span className="pv-pc-price">8.840.000 ₫</span>
                    <span className="pv-pc-oldprice">11.449.000 ₫</span>
                    <span className="pv-pc-discount">-22,79%</span>
                  </div>
                  <div className="pv-pc-gift">
                    <Image
                      src="/assets/client/img/anhtemp/gift1.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift2.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                    <Image
                      src="/assets/client/img/anhtemp/gift3.png"
                      alt=""
                      width={100}
                      height={100}
                    />
                  </div>
                  <button className="btn pv-pc-btn">Thêm vào giỏ</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      {/* <!-- ********************END SECTION 7-  contain product pages mini******************** --> */}
    </>
  );
}
