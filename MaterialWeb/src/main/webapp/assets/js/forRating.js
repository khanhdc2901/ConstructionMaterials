/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function iRateXStar(n) {
    // Lấy tất cả các sao
    const stars = document.querySelectorAll(".star");

    // Lặp qua từng sao
    stars.forEach(star => {
        const index = parseInt(star.getAttribute("data-index"));
        if (index <= n) {
            star.classList.remove("bi-star");
            star.classList.add("bi-star-fill");
        } else {
            star.classList.remove("bi-star-fill");
            star.classList.add("bi-star");
        }
    });

    // Gán giá trị rating vào input hidden
    document.getElementById("rating").value = n;
}
