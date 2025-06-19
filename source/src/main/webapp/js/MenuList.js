const popup = document.getElementById("popupCommodity");
popup.style.display = "none";

function popupCommodity(id, image, name, price) {
	popup.classList.add("open");
	document.getElementById("commodityId").value = id;
	document.getElementById("commodityImage").value = image;
	document.getElementById("commodityName").value = name;
	document.getElementById("commodityPrice").value = price;

	var nowQuantity = window.sessionStorage.getItem([id]);
	if (nowQuantity != null) {
		nowQuantity = parseInt(nowQuantity);
		document.getElementById("quantity").value = nowQuantity;
	}
	else {
		document.getElementById("quantity").value = 0;
	}
	
	document.getElementById("commodityPrice").value = price;

	popup.style.display = "block";
}

function addCart() {
	const id = document.getElementById("commodityId").value;
	var quantity = document.getElementById("quantity").value;
	quantity = parseInt(quantity);

	window.sessionStorage.setItem([id], [quantity]);

	popup.style.display = "none";
}

function closePopup() {
	popup.style.display = "none";
}

function submitOrder() {
	const cartInputs = document.getElementById("cartInputs");
	cartInputs.innerHTML = "";

	for (let i = 0; i < sessionStorage.length; i++) {
		const key = sessionStorage.key(i);
		const value = sessionStorage.getItem(key);

		if (0 < parseInt(value)) {
			const input = document.createElement("input");
			input.type = "hidden";
			input.name = "cart_" + key;
			input.value = value;
			cartInputs.appendChild(input);
		}
	}

	document.getElementById("orderForm").submit();
}
