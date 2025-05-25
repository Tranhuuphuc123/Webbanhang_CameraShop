"use client";
import { useEffect } from "react";

//import css của trang client
import "@/../public/assets/client/css/stylesClient.css";

//iumport component riêng
import Header from "@/components/client/header";
import Footer from "@/components/client/footer";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  //sử dụng useEffect để dẫn bootstrap js bào layout của client
  useEffect(() => {
    //import bootstrap bundle
    import("bootstrap/dist/js/bootstrap.min.js")
      .then(() => console.log("Bootstrap Bundle Loaded"))
      .catch((err) => console.error("Bootstrap Load Error", err));

    //load external my JavaScript cá nhân tự viết
    const script = document.createElement("script");
    script.src = "/assets/client/js/script_client.js";
    script.async = true;
    document.body.appendChild(script);

    return () => {
      document.body.removeChild(script); //cleanup script
    };
  }, []);

  //trả về giao diện thiết kế
  return (
    <>
      {/* phần header */}
      <Header />
      {/*end phần header */}

      {/* phần body của trang client */}
      {children}
      {/* end phần body của trang client */}

      {/* phần footer */}
      <footer>
        <Footer />
      </footer>
      {/* end phần footer */}
    </>
  );
}
