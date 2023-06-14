//
//     function redirectToBooking() {
//     var selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
//     var bookingIds = [];
//     selectedItems.forEach(function(item) {
//     bookingIds.push(item.value);
// });
//     window.location.href = "booking.jsp?bookingIds=" + bookingIds.join(",");
// }
//
//     function deleteSelectedItems() {
//     var selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
//     var itemIds = [];
//     selectedItems.forEach(function(item) {
//     itemIds.push(item.value);
// });
//     // Gửi request tới servlet để xóa các item có IDs trong danh sách itemIds
//     // Sử dụng Ajax hoặc gửi form POST đến servlet
//     // Sau khi xóa thành công, tải lại trang cart.jsp hoặc cập nhật danh sách cartItems
// }
function redirectToBooking() {
    var selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
    var bookingIds = [];
    selectedItems.forEach(function(item) {
        bookingIds.push(item.value);
    });

    if (bookingIds.length > 0) {
        // Chuyển hướng người dùng đến trang booking.jsp nếu có ít nhất một mục đã được chọn
        window.location.href = "booking.jsp?bookingIds=" + bookingIds.join(",");
    } else {
        // Hiển thị thông báo cho người dùng nếu không có mục nào được chọn
        alert("Please select at least one item.");
    }
}

function deleteSelectedItems() {
    var selectedItems = document.querySelectorAll('input[name="selectedItems"]:checked');
    var itemIds = [];
    selectedItems.forEach(function(item) {
        itemIds.push(item.value);
    });

    if (itemIds.length > 0) {
        // Gửi request tới servlet để xóa các mục có IDs trong danh sách itemIds
        // Sử dụng Ajax hoặc gửi form POST đến servlet
        // Sau khi xóa thành công, tải lại trang cart.jsp hoặc cập nhật danh sách cartItems
        // ...
    } else {
        // Hiển thị thông báo cho người dùng nếu không có mục nào được chọn
        alert("Please select at least one item.");
    }
}
