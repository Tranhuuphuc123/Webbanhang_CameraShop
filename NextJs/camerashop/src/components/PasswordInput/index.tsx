/* đây là phần xử lý logic của js thuần xử lý ẩn/hiện password của 
form login(page.tsx của form login):

 <=> tức là chuyển đoạn code sau thành xử lý component trong nextjs

          document.querySelectorAll(".toggle-password").forEach(function (el) {
            el.addEventListener("click", function () {
              const input = this.parentElement.querySelector("input");
              if (input.type === "password") {
                input.type = "text";
                this.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
              } else {
                input.type = "password";
                this.innerHTML = '<i class="fa-regular fa-eye"></i>';
              }
            });
          });
*/
"use client";

/* useRef: là một hook trong React/NextJs dùng để tham chiếu(reference) đến một phần tử DOM 
hoặc một giá trị bất ký mà bạn muốn giữ nguyên giữa các lần render của component */
import { useState, useRef } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";

export default function PasswordInput() {
  const [showPassword, setShowPassword] = useState(false);

  //useRef: tạo một tham chiếu đến phần tử input để có thể thao tác với nó
  //HTMLInputElement: kiểu dữ liệu của phần tử input trong TypeScript
  //null: giá trị ban đầu của tham chiếu
  const inputRef = useRef<HTMLInputElement>(null);

  const togglePassword = () => {
    setShowPassword((prev) => !prev);
    if (inputRef.current) {
      inputRef.current.type = showPassword ? "text" : "password";
    }
  };

  return (
    //relative: làm cho phần tử cha làm mốc để đặt vị trí của phần tử con
    <div className="relative">
      <input
        type={showPassword ? "text" : "password"}
        ref={inputRef}
        id="password"
        className="form-control"
        placeholder="Nhập mật khẩu"
        required
      />
      <span onClick={togglePassword} className="toggle-password">
        {showPassword == true && (
          <FontAwesomeIcon icon={faEyeSlash} className="me-2" />
        )}
        {!showPassword == true && (
          <FontAwesomeIcon icon={faEye} className="me-2" />
        )}
      </span>
    </div>
  );
}
