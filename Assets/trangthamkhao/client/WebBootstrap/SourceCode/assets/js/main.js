// active navbar
let nav = document.querySelector('.navigation-wrap');
window.onscroll = function () {
    if (document.documentElement.scrollTop > 20) {
        nav.classList.add("scroll-on");
    } else {
        nav.classList.remove("scroll-on");
    }
}

// nav hide
let navBar = document.querySelectorAll('.nav-link');
let navCollapse = document.querySelector('.navbar-collapse.collapse'); // Collapse
navBar.forEach(function (a) {
    a.addEventListener("click", function () {
        navCollapse.classList.remove("show");
    })
})

// =====================
// SECTION 3: FLASH SALE TABS UI - homepage
// =====================
document.addEventListener('DOMContentLoaded', function() {
  const tabBtns = document.querySelectorAll('.pv-flashsale-tab');
  const tabContent = document.querySelector('.pv-flashsale-tab-content');

  tabBtns.forEach(btn => {
    btn.addEventListener('mouseenter', function() {
      tabBtns.forEach(b => b.classList.remove('hovered'));
      btn.classList.add('hovered');
      if(tabContent) tabContent.classList.add('pv-flashsale-tab-content-hovered');
    });
    btn.addEventListener('mouseleave', function() {
      btn.classList.remove('hovered');
      if(tabContent) tabContent.classList.remove('pv-flashsale-tab-content-hovered');
    });
    btn.addEventListener('click', function() {
      tabBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
    });
  });
});
// =====================
// END SECTION 3
// =====================

