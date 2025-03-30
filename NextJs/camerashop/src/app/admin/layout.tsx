//import các components cần thiết
"use client";
import { useEffect } from "react";

import "@/../public/assets/admin/css/style.css";
import sidebar  from "@/components/admin/sidebar";
import nav from "@/components/admin/nav";



// cấu trúc layout của trang admin pages
const AdminLayout = ({
    children,
  }: Readonly<{
    children: React.ReactNode;
  }>) => {

    //dùng useEffect để thực hiện một hành động khi component được render
    //=> chủ yếu đây xử lý link script và thực hiện các hành động khi component được render
    useEffect(() => {
      // Import Bootstrap Bundle
      import("bootstrap/dist/js/bootstrap.bundle.min.js")
        .then(() => console.log("Bootstrap Bundle Loaded"))
        .catch((err) => console.error("Bootstrap Load Error", err));
        
      // Load external my JavaScript cá nhân tự viết
      const script = document.createElement("script");
      script.src = "/assets/admin/js/script.js"; // Ensure this exists in the public folder
      script.async = true;
      document.body.appendChild(script);

      return () => {
        document.body.removeChild(script); // Cleanup script
      };
    }, []);



    return (
        <>
            <div className="wrapper">
              
                {/* <!-- Sidebar --> */}
                <aside id="sidebar">
                   {sidebar()}
                </aside>
                {/* <!-- Sidebar end --> */}

                {/* main content */}
                <div className="main">
                    {/* nav header */}
                    <nav className="navbar navbar-expand px-3 border-bottom">
                        {nav()}
                    </nav>
                    {/* nav header end */}

                    {/* main container */}
                    <main className="content px-3 py-2">

                         {/* <!--body container --> */}
                        <div className="container">
                            {children}
                        </div>  
                        {/* <!--body container end--> */}   

                    </main>
                    {/* main body container end*/}
                        
                </div>
            </div>
        </>
       
    );
}


export default AdminLayout;   