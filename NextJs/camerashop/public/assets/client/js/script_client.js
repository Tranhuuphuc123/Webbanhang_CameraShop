// active navbar
let nav = document.querySelector(".navigation-wrap");
window.onscroll = function () {
  if (document.documentElement.scrollTop > 20) {
    nav.classList.add("scroll-on");
  } else {
    nav.classList.remove("scroll-on");
  }
};

// nav hide
let navBar = document.querySelectorAll(".nav-link");
let navCollapse = document.querySelector(".navbar-collapse.collapse"); // Collapse
navBar.forEach(function (a) {
  a.addEventListener("click", function () {
    navCollapse.classList.remove("show");
  });
});

/**************************************************************************************************** */

// =====================
// SCRIPT XỬ LÝ CHO PHẦN PRODUCT DETAILS: <!-- JS đổi ảnh gallery -->
// =====================\
document.querySelectorAll(".gallery-thumb").forEach(function (thumb) {
  thumb.addEventListener("click", function () {
    document.getElementById("mainProductImg").src = this.dataset.img;
    document
      .querySelectorAll(".gallery-thumb")
      .forEach((t) => t.classList.remove("active"));
    this.classList.add("active");
  });
});
// Nút tới lui
const thumbs = Array.from(document.querySelectorAll(".gallery-thumb"));
let current = 0;
function showImg(idx) {
  if (idx < 0) idx = thumbs.length - 1;
  if (idx >= thumbs.length) idx = 0;
  thumbs[idx].click();
  current = idx;
}
document.querySelector(".gallery-prev").onclick = () => showImg(current - 1);
document.querySelector(".gallery-next").onclick = () => showImg(current + 1);

// ===== PAGE_DETAIL - JS cho phần mô tả sản phẩm (session3) =====
document.addEventListener("DOMContentLoaded", function () {
  var btn = document.getElementById("descToggleBtn");
  var more = document.getElementById("descMore");
  if (btn && more) {
    btn.addEventListener("click", function (e) {
      setTimeout(function () {
        if (btn.getAttribute("aria-expanded") === "true") {
          btn.innerText = "Ẩn bớt";
        } else {
          btn.innerText = "Đọc thêm";
          // Scroll về đầu mô tả khi rút gọn
          document
            .getElementById("descContent")
            .scrollIntoView({ behavior: "smooth", block: "start" });
        }
      }, 200);
    });
  }
});
// ===== END PAGE_DETAIL - JS cho phần mô tả sản phẩm =====

// ===== PAGE_DETAIL - JS cho phần bình luận khách hàng (session4) =====
document.addEventListener("DOMContentLoaded", function () {
  // Filter đánh giá
  document
    .querySelectorAll(".review-filter-group .btn")
    .forEach(function (btn) {
      btn.addEventListener("click", function () {
        document
          .querySelectorAll(".review-filter-group .btn")
          .forEach((b) => b.classList.remove("active"));
        btn.classList.add("active");
        // Demo: chỉ highlight, không lọc thật
      });
    });

  // Gửi bình luận
  var reviewForm = document.querySelector(".review-form");
  if (reviewForm) {
    reviewForm.addEventListener("submit", function (e) {
      e.preventDefault();
      var input = reviewForm.querySelector('input[type="text"]');
      var val = input.value.trim();
      if (val) {
        var list = document.querySelector(".review-list");
        var item = document.createElement("div");
        item.className = "review-item d-flex align-items-start mb-3";
        item.innerHTML = `<div class="review-avatar rounded-circle bg-secondary text-white d-flex align-items-center justify-content-center me-3" style="width:40px;height:40px;">B</div>
          <div class="flex-grow-1">
            <div class="d-flex align-items-center gap-2 mb-1">
              <span class="fw-bold">Bạn</span>
              <span class="text-muted small">• Vừa xong</span>
            </div>
            <div class="review-content mb-1">${val}</div>
            <div class="d-flex align-items-center gap-2">
              <button class="btn btn-light btn-sm px-2 py-0 review-like"><i class="fa fa-thumbs-up"></i> 0</button>
              <button class="btn btn-light btn-sm px-2 py-0 review-reply">Trả lời</button>
            </div>
          </div>`;
        list.prepend(item);
        input.value = "";
      }
    });
  }

  // Like bình luận
  document.addEventListener("click", function (e) {
    if (e.target.closest(".review-like")) {
      var btn = e.target.closest(".review-like");
      var num = btn.innerText.replace(/\D/g, "") || "0";
      btn.innerHTML = `<i class='fa fa-thumbs-up'></i> ${parseInt(num) + 1}`;
    }
    // Reply bình luận
    if (e.target.closest(".review-reply")) {
      alert("Chức năng trả lời sẽ được phát triển sau!");
    }
  });
});
// ===== END PAGE_DETAIL - JS cho phần bình luận khách hàng =====

// ===== PAGE_DETAIL - JS cho phần FAQ (session5) =====
document.addEventListener("DOMContentLoaded", function () {
  // Đảm bảo icon +/-, chỉ 1 câu hỏi mở
  function updateFaqIcons() {
    document
      .querySelectorAll(".product-faq-accordion .accordion-button")
      .forEach(function (b) {
        var icon = b.querySelector(".faq-toggle-icon");
        if (icon) {
          if (b.classList.contains("collapsed")) {
            icon.innerHTML = "<span>+</span>";
          } else {
            icon.innerHTML = "<span>-</span>";
          }
        }
      });
  }
  updateFaqIcons(); // Gọi khi load trang
  document
    .querySelectorAll(".product-faq-accordion .accordion-button")
    .forEach(function (btn) {
      btn.addEventListener("click", function () {
        setTimeout(updateFaqIcons, 200);
      });
    });
});
// ===== END PAGE_DETAIL - JS cho phần FAQ =====

// ===== CART PAGE - JS GIỎ HÀNG (1 COLUMN) =====
document.addEventListener("DOMContentLoaded", function () {
  // Chọn tất cả
  var selectAll = document.getElementById("cartSelectAll");
  var itemChecks = document.querySelectorAll(".cart-item-check");
  if (selectAll && itemChecks.length) {
    selectAll.addEventListener("change", function () {
      itemChecks.forEach(function (chk) {
        chk.checked = selectAll.checked;
      });
    });
    itemChecks.forEach(function (chk) {
      chk.addEventListener("change", function () {
        selectAll.checked = Array.from(itemChecks).every((c) => c.checked);
      });
    });
  }
  // Xóa sản phẩm
  document.querySelectorAll(".cart-item-remove").forEach(function (btn) {
    btn.addEventListener("click", function () {
      btn.closest(".cart-item-box").remove();
    });
  });
  // Tăng giảm số lượng
  document.querySelectorAll(".cart-qty-group").forEach(function (group) {
    var input = group.querySelector(".cart-qty-input");
    group.querySelector(".cart-qty-minus").onclick = function () {
      var val = parseInt(input.value) || 1;
      if (val > 1) input.value = val - 1;
    };
    group.querySelector(".cart-qty-plus").onclick = function () {
      var val = parseInt(input.value) || 1;
      input.value = val + 1;
    };
  });
  // (Demo) Cập nhật tổng tiền: có thể thêm code tính toán lại tổng tiền khi xóa/tăng giảm số lượng
});
// ===== END CART PAGE - JS GIỎ HÀNG =====
