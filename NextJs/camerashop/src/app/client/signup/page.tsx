"use client";
import React from "react";

//sử dụng icon của lib fontAwesome
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";

//import component xử lý ẩn hiện password của mục input password trong form login
import PasswordInput from "@/components/PasswordInput";

const Signup = () => {
  return (
    <>
      <div className="container-fluid py-5" style={{ minHeight: "100vh" }}>
        <div
          className="signup-form-box bg-white p-4 p-md-5 rounded-3 shadow-sm w-100"
          style={{ maxWidth: "100%", margin: "0 auto" }}
        >
          <div className="text-center mb-4">
            <FontAwesomeIcon
              icon={faGoogle}
              style={{ height: "54px", color: "#ea4335" }}
            />
            <h3 className="fw-bold mt-3 mb-1">Create an Account</h3>
            <div className="text-muted mb-2" style={{ fontSize: "1rem" }}>
              Sign up now to get started with an account.
            </div>
          </div>
          <div className="d-flex justify-content-center">
            <button className="btn btn-light w-20 border d-flex align-items-center justify-content-center mb-3 google-btn">
              <FontAwesomeIcon
                icon={faGoogle}
                style={{ height: "20px", width: "20px", marginRight: 8 }}
              />
              Sign up with Google
            </button>
          </div>
          <div className="d-flex align-items-center mb-3">
            <hr className="flex-grow-1" />
            <span className="mx-2 text-muted small">OR</span>
            <hr className="flex-grow-1" />
          </div>
          <form>
            <div className="row mb-3 align-items-center">
              <label
                htmlFor="fullname"
                className="col-md-3 col-form-label text-md-end"
              >
                Full Name<span className="text-danger">*</span>
              </label>
              <div className="col-md-9">
                <input
                  type="text"
                  className="form-control"
                  id="fullname"
                  placeholder="John Doe"
                  required
                />
              </div>
            </div>
            <div className="row mb-3 align-items-center">
              <label
                htmlFor="email"
                className="col-md-3 col-form-label text-md-end"
              >
                Email Address<span className="text-danger">*</span>
              </label>
              <div className="col-md-9">
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  placeholder="example@email.com"
                  required
                />
              </div>
            </div>
            <div className="row mb-3 align-items-center position-relative">
              <label
                htmlFor="password"
                className="col-md-3 col-form-label text-md-end"
              >
                Password<span className="text-danger">*</span>
              </label>
              <div className="col-md-9 position-relative">
                {/* <input
                  type="password"
                  className="form-control"
                  id="password"
                  placeholder="Password"
                  required
                />
                <span className="toggle-password">
                  <i className="fa-regular fa-eye"></i>
                </span> */}
                <PasswordInput />
              </div>
            </div>
            <div className="row mb-3 align-items-center position-relative">
              <label
                htmlFor="confirmpassword"
                className="col-md-3 col-form-label text-md-end"
              >
                Confirm Password<span className="text-danger">*</span>
              </label>
              <div className="col-md-9 position-relative">
                {/* <input
                  type="password"
                  className="form-control"
                  id="confirmpassword"
                  placeholder="Confirm Password"
                  required
                />
                <span className="toggle-password">
                  <i className="fa-regular fa-eye"></i>
                </span> */}
                <PasswordInput />
              </div>
            </div>
            <div className="row mb-3">
              <div className="offset-md-3 col-md-9">
                <div className="form-check">
                  <input
                    className="form-check-input"
                    type="checkbox"
                    id="terms"
                    required
                  />
                  <label className="form-check-label small" htmlFor="terms">
                    I have read and agree to the
                    <a href="#" className="text-primary text-decoration-none">
                      Terms of Service
                    </a>
                  </label>
                </div>
              </div>
            </div>
            <div className="row mb-3">
              <div className="offset-md-3 col-md-9">
                <button
                  type="submit"
                  className="btn btn-primary w-30 mb-3"
                  style={{ fontWeight: 500 }}
                >
                  Get Started
                </button>
              </div>
            </div>
          </form>
          <div className="text-center small mt-2">
            Already have an account?
            <a
              href="loginform.html"
              className="fw-bold text-primary text-decoration-none"
            >
              Log in
            </a>
          </div>
        </div>
      </div>
    </>
  );
};
export default Signup;
