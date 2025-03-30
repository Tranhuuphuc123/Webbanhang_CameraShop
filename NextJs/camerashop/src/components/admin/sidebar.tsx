import Image from "next/image";
import Link from "next/link";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faHouse, faBox, faCartArrowDown, faUser,
        faRightToBracket
        } from '@fortawesome/free-solid-svg-icons'


const sidebar = () => {
  return(
    <>
         <div className="h-100">
                <div className="sidebar-logo">
                    <Link href="#"> 
                        <Image
                            src="/assets/admin/img/IZJP.gif"
                            width={50} // Chiều rộng
                            height={50} // Chiều cao
                            alt="Logo"
                            style={{ borderRadius: "50%" }}
                            />
                          CAMERASHOP
                     </Link>
                </div>
                {/* <!-- Sidebar Navigation --> */}
                <ul className="sidebar-nav">
                    <li className="sidebar-header">
                        Tools & Components
                    </li>
                    <li className="sidebar-item">
                        <Link href="#" className="sidebar-link">
                            <FontAwesomeIcon icon={faHouse} className="fa-fw" />
                            &nbsp; Dashboard
                        </Link>
                    </li>
                    <li className="sidebar-item">
                        <Link href="/admin/products" className="sidebar-link collapsed" data-bs-target="#pages"
                            aria-expanded="false" aria-controls="pages">
                             <FontAwesomeIcon icon={faBox} className="fa-fw" />
                           &nbsp; Products
                        </Link>
                    </li>
                    <li className="sidebar-item">
                        <Link href="#" className="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard"
                            aria-expanded="false" aria-controls="dashboard">
                             <FontAwesomeIcon icon={faCartArrowDown} className="fa-fw" />
                            &nbsp; Cart
                        </Link>
                        <ul id="dashboard" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li className="sidebar-item">
                                <Link href="#" className="sidebar-link">Dashboard Analytics</Link>
                            </li>
                            <li className="sidebar-item">
                                <Link href="#" className="sidebar-link">Dashboard Ecommerce</Link>
                            </li>
                        </ul>
                    </li>
                    <li className="sidebar-item">
                        <Link href="#" className="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#auth"
                            aria-expanded="false" aria-controls="auth">
                            <FontAwesomeIcon icon={faUser} className="fa-fw" />
                            &nbsp; Account
                        </Link>
                        <ul id="auth" className="sidebar-dropdown list-unstyled collapse" data-bs-parent="#sidebar">
                            <li className="sidebar-item">
                                <Link href="#" className="sidebar-link">
                                    <FontAwesomeIcon icon={faRightToBracket} className="fa-fw" /> &nbsp;Login
                                </Link>
                            </li>
                            <li className="sidebar-item">
                                <Link href="#" className="sidebar-link">
                                     <FontAwesomeIcon icon={faRightToBracket} className="fa-fw" /> &nbsp;Register
                                 </Link>
                            </li>
                        </ul>
                    </li>
                    
                </ul>
            </div>
    </>
  )
}
export default sidebar;

