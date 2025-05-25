"use client"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faYoutube, faTiktok } from '@fortawesome/free-brands-svg-icons';
import Image from "next/image";

const Footer = () => {
    return(
        <>
            <div className="footer-main pt-5 pb-3">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-3 col-md-6 mb-4 mb-lg-0">
                            <h6 className="mb-3">KẾT NỐI VỚI FPT SHOP</h6>
                        <div className="footer-social mb-3">
                            <a href="#"><FontAwesomeIcon icon={faFacebook} className="fa-fw"/></a>
                            <a href="#">
                                <Image
                                    src="https://static.zalo.me/logo/zalo-logo.svg"
                                    alt="Zalo"
                                    width={24}
                                    height={24}
                                    style={{ background: '#fff', borderRadius: '50%', padding: 2 }}
                                />
                            </a>
                            <a href="#"><FontAwesomeIcon icon={faYoutube} /></a>
                            <a href="#"><FontAwesomeIcon icon={faTiktok} /></a>
                        </div>
                        <div className="small mb-1">TỔNG ĐÀI MIỄN PHÍ</div>
                            <div className="small">Tư vấn mua hàng: <b>1800.6601</b> (Nhánh 1)</div>
                            <div className="small">Hỗ trợ kỹ thuật: <b>1800.6601</b> (Nhánh 2)</div>
                            <div className="small">Góp ý, khiếu nại: <b>1800.6616</b> (8h00 - 22h00)</div>
                        </div>
                        <div className="col-lg-3 col-md-6 mb-4 mb-lg-0">
                            <h6 className="mb-3">VỀ CHÚNG TÔI</h6>
                            <ul className="list-unstyled">
                                <li><a href="#">Giới thiệu về công ty</a></li>
                                <li><a href="#">Quy chế hoạt động</a></li>
                                <li><a href="#">Dự án Doanh nghiệp</a></li>
                                <li><a href="#">Tin tức khuyến mại</a></li>
                                <li><a href="#">Giới thiệu máy đổi trả</a></li>
                                <li><a href="#">Hướng dẫn mua hàng & thanh toán online</a></li>
                                <li><a href="#">Đại lý uỷ quyền & TTBH uỷ quyền của Apple</a></li>
                                <li><a href="#">Tra cứu hoá đơn điện tử</a></li>
                                <li><a href="#">Tra cứu bảo hành</a></li>
                                <li><a href="#">Câu hỏi thường gặp</a></li>
                            </ul>
                        </div>

                        <div className="col-lg-3 col-md-6 mb-4 mb-lg-0">
                            <h6 className="mb-3">CHÍNH SÁCH</h6>
                            <ul className="list-unstyled">
                                <li><a href="#">Chính sách bảo hành</a></li>
                                <li><a href="#">Chính sách đổi trả</a></li>
                                <li><a href="#">Chính sách bảo mật</a></li>
                                <li><a href="#">Chính sách trả góp</a></li>
                                <li><a href="#">Chính sách khui hộp sản phẩm</a></li>
                                <li><a href="#">Chính sách giao hàng & lắp đặt</a></li>
                                <li><a href="#">Chính sách mang đi động FPT</a></li>
                                <li><a href="#">Chính sách thu thập & xử lý dữ liệu cá nhân</a></li>
                                <li><a href="#">Quy định về hỗ trợ kỹ thuật & sao lưu dữ liệu</a></li>
                                <li><a href="#">Chính sách giao hàng & lắp đặt Điện máy, Gia dụng</a></li>
                            </ul>
                        </div>

                        <div className="col-lg-3 col-md-6 mb-4 mb-lg-0">
                            <h6 className="mb-3">HỖ TRỢ THANH TOÁN</h6>
                            <div className="mb-3">
                                <Image src="/assets/client/img/visa.png" alt="Visa" 
                                    width={40} 
                                    height={24} 
                                    className="img-fluid mb-2" 
                                    style={{ maxWidth: '40px' }} />

                                <Image src="/assets/client/img/visamastercard.png" 
                                        alt="visamastercard" 
                                        width={40} height={24} 
                                        className="img-fluid mb-2" 
                                        style={{ maxWidth: '40px' }} />

                                <Image src="/assets/client/img/momo.jpg" 
                                    alt="Momo" width={40} height={24} 
                                    className="img-fluid mb-2" 
                                    style={{ maxWidth: '40px' }} />

                                <Image src="/assets/client/img/vnpay.png" 
                                    alt="VNPAY" width={40} height={24} 
                                    className="img-fluid mb-2" 
                                    style={{ maxWidth: '40px' }} />
                            </div>

                            <h6 className="mb-2">CHỨNG NHẬN</h6>
                            <Image src="/assets/client/img/dcma.jpg" 
                                    alt="DMCA" width={60} height={24} 
                                    className="img-fluid me-2" 
                                    style={{ maxWidth: '60px' }} />
                            <Image src="/assets/client/img/bocongthuong.png" 
                                    alt="Bộ Công Thương" 
                                    width={60} height={24} 
                                    className="img-fluid" 
                                    style={{ maxWidth: '60px' }} />
                        </div>
                    </div>

                    <hr className="my-4" />

                    <div className="row">
                        <div className="col-12 text-center small">
                        © 2025 Camera Shop. All Rights Reserved. Địa chỉ: Khu II, đường 3/2, P. Xuân Khánh, Q. Ninh Kiều, TP. Cần Thơ.<br />
                        Điện thoại: (0292) 3832 663 - Email: dhct@ctu.edu.vn
                        </div>
                    </div>
                </div>
            </div>
        </>
    
    )
}

export default Footer;