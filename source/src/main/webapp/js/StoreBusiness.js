
// 注文用
function fetchOrderData() {
	fetch(`${contextPath}/VisitorOrderServlet`)
		.then(response => {
			if (!response.ok) throw new Error('response not ok');
			return response.json();
		})
		.then(data => {
			console.log('注文データ:', data);
			updateOrderList(data);
		})
		.catch(error => {
			console.error('注文取得エラー:', error);
		});
}

fetchOrderData();

setInterval(fetchOrderData, 5000);

function updateOrderList(data) {
	const listContainer = document.getElementById("orderList");
	listContainer.innerHTML = "";

	data.forEach(order => {
		const item = document.createElement("div");
		item.className = "order";
		item.textContent = `${order.customerName} - ${order.commodityName} × ${order.quantity}`;
		console.log(order.customerName, order.commodityName, order.quantity);

		const button = document.createElement("button");
		button.textContent = "X";
		button.className = "deleteBtn";

		button.addEventListener("click", () => {
			item.style.display = "none";
		});

		item.appendChild(button);
		listContainer.appendChild(item);
	});
}



// 来店者
document.addEventListener("DOMContentLoaded", () => {
	const button = document.querySelectorAll(".toggleBtn");

	button.forEach(btn => {
		btn.addEventListener("click", () => {
			const details = btn.closest(".visitor").querySelector(".visitorDetails");
			var isVisible = details.style.display === "block";

			details.style.display = isVisible ? "none" : "block";
			btn.textContent = isVisible ? "▽" : "△";
		});
	});
});

// 会計
function AccountingGet(visitId) {
	const accountingInputs = document.getElementById("accountingInput");
	accountingInputs.innerHTML = "";

	const input = document.createElement("input");
	input.type = "hidden";
	input.name = "visitId";
	input.value = visitId;
	accountingInputs.appendChild(input);

	document.getElementById("accountingForm").submit();
}

