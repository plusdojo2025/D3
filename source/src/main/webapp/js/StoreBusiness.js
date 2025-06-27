
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

function fetchOrderData() {
	fetch(`${contextPath}/VisitorOrderServlet`)
		.then(response => {
			if (!response.ok) throw new Error('response not ok');
			return response.json();
		})
		.then(data => {
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
		item.style.display = "none";

		// 完了表示
		const orderStatusMap = getStatusMap();
		const showComplete = document.getElementById("showComplete").checked;

		const orderId = order.orderId;
		const status = orderStatusMap[orderId] || "未処理";

		if (status === "未処理" || showComplete) {
			const button = document.createElement("button");
			button.textContent = status;
			button.className = "changeStatusBtn";
			item.style.display = "block";

			button.addEventListener("click", () => {
				const next = nextStatus(status);
				orderStatusMap[orderId] = next;
				button.textContent = next;
				
				saveStatusMap(orderStatusMap);
				fetchOrderData();
			});

			item.appendChild(button);
		}
		listContainer.appendChild(item);
	});
}

function fetchOrder() {
	fetchOrderData();
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

