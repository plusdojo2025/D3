
// 注文用
function getStatusMap() {
	try {
		const data = localStorage.getItem("orderStatusMap");
		return data ? JSON.parse(data) : {};
	} catch (e) {
		console.error("failed orderStatusMap loaded", e);
		localStorage.removeItem("orderStatusMap");
		return {};
	}
}
function saveStatusMap(map) {
	localStorage.setItem("orderStatusMap", JSON.stringify(map));
}
function nextStatus(current) {
	if (current === "未処理")
		return "完了";
	return "未処理";
}

window.addEventListener("DOMContentLoaded", () => {

	function fetchOrders() {
		const orderStatusMap = getStatusMap();
		const showComplete = document.getElementById("showComplete").checked;

		fetch(`${location.origin}${location.pathname.replace(/\/[^\/]*$/, '')}/VisitorOrderServlet`)
			.then(response => response.json())
			.then(data => {
				const container = document.getElementById("orderList");
				container.innerHTML = "";

				data.forEach(order => {
					const block = document.createElement("div");
					block.className = "orderBlock";

					const orderId = order.orderId;
					const status = orderStatusMap[orderId] || "未処理";

					if (status === "完了" && !showComplete)
						return

					block.textContent = `${order.customerName} ${order.commodityName} × ${order.quantity} ${status}`;

					const btn = document.createElement("button");
					btn.textContent = "X";
					btn.addEventListener("click", () => {
						const next = nextStatus(status);
						orderStatusMap[orderId] = next;
						saveStatusMap(orderStatusMap);
						fetchOrders();
					});

					block.appendChild(btn);
					container.appendChild(block);
				});
			})
			.catch(error => {
				console.error("注文取得エラー:", error);
			});
	}

	fetchOrders();
	setInterval(fetchOrders, 5000);

	document.getElementById("showComplete").addEventListener("change", fetchOrders);
});



// 来店者
document.addEventListener("DOMContentLoaded", () => {
	const button = document.querySelectorAll(".toggleBtn");

	button.forEach(btn => {
		btn.addEventListener("click", () => {
			const details = btn.closest(".visitor").querySelector(".visitorDetails");
			var isVisible = details.style.display === "display";

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
