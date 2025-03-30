import Image from "next/image";
import Link from "next/link";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
//lưu ý các faBell,... có thể tra trong trang chủ fontAwesome và thêm vào
import {faBell, faEnvelope} from '@fortawesome/free-solid-svg-icons'


const nav = () => {
  return(
    <>
        {/* <!-- Button for sidebar toggle --> */}
        <button className="btn led-toggler" type="button" data-bs-theme="dark">
            <span className="navbar-toggler-icon"></span>
        </button>
        {/* <!--search admin page--> */}
        {/* <input type="text" className="form-control w-25 ms-3" placeholder="Search..."/> */}
        <div className="dropdown ms-auto">
            <button className="btn btn-outline-light led-togglers me-2"> <FontAwesomeIcon icon={faBell} className="fa-fw" /></button>
            <button className="btn btn-outline-light led-toggler me-2"><FontAwesomeIcon icon={faEnvelope} className="fa-fw" /></button>
            <button className="btn btn-secondary dropdown-toggle led-toggler" type="button" id="avatarMenu" data-bs-toggle="dropdown">
                <Image 
                    src="/assets/admin/img/user.jpg" 
                    width={30} 
                    height={30} 
                    alt="User Avatar" 
                    style={{ borderRadius: "50%" }}
                />
            </button>
            <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="avatarMenu">
                <li><Link className="dropdown-item" href="#"><i className="fa fa-user"></i> Profile</Link></li>
                <li><Link className="dropdown-item" href="#"><i className="fa fa-cog"></i> Settings</Link></li>
                <li><Link className="dropdown-item" href="#"><i className="fa fa-sign-out-alt"></i> Logout</Link></li>
            </ul>
        </div>
    </>
  )
}
export default nav;