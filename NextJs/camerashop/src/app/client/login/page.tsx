/**MỤC THIẾT KẾ GIAO DIỆN CỦA FORM LOGIN**/
"use client";
import React from "react";
import Link from "next/link";
import Script from "next/script";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";

//import component xử lý ẩn hiện password của mục input password trong form login
import PasswordInput from "@/components/PasswordInput";

const Login = () => {
  //trả về giao diện form login trong nextjs
  return (
    <>
      <div className="container d-flex justify-content-center align-items-center min-vh-100 mb-1">
        <div
          className="login-form-box bg-white p-1 p-md-5 rounded-3 shadow-sm w-100"
          style={{ maxWidth: "100%" }}
        >
          <div className="text-center mb-4">
            <FontAwesomeIcon
              icon={faGoogle}
              className="fa-3x"
              style={{ color: "#4285f4" }}
            />
            <h4 className="fw-bold mt-3 mb-1">Log in to your Account</h4>
            <div className="text-muted mb-2" style={{ fontSize: "1rem" }}>
              Welcome back, please enter your details.
            </div>
          </div>
          <button className="btn btn-light w-100 border d-flex align-items-center justify-content-center mb-3 google-btn">
            <FontAwesomeIcon icon={faGoogle} className="me-2" /> Continue with
            Google
          </button>
          <div className="d-flex align-items-center mb-3">
            <hr className="flex-grow-1" />
            <span className="mx-2 text-muted small">OR</span>
            <hr className="flex-grow-1" />
          </div>
          <form>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email Address
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="johndoe@gmail.com"
                required
              />
            </div>
            <div className="mb-3 position-relative">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              {/* <input
                type="password"
                className="form-control"
                id="password"
                placeholder="Password"
                required
              />
              <span className="toggle-password">
                <FontAwesomeIcon icon={faEye} className="fa-solid" />
              </span> */}
              <PasswordInput />
            </div>
            <div className="d-flex justify-content-between align-items-center mb-3">
              <div className="form-check">
                <input
                  className="form-check-input"
                  type="checkbox"
                  id="remember"
                />
                <label className="form-check-label" htmlFor="remember">
                  Remember me
                </label>
              </div>
              <a href="#" className="fw-bold small text-decoration-none">
                Forgot Password?
              </a>
            </div>
            <button
              type="submit"
              className="btn btn-primary w-100 mb-3"
              style={{ fontWeight: 500 }}
            >
              Log in
            </button>
          </form>
          <div className="text-center small mt-2">
            Don&apos;t have an account?
            <Link
              href="/client/signup"
              className="fw-bold text-primary text-decoration-none"
            >
              Sign Up
            </Link>
          </div>
        </div>
      </div>

      <Script
        src="/assets/client/js/login_asignin.js"
        strategy="afterInteractive"
      />
    </>
  );
};
export default Login;
