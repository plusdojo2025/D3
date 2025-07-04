
// サイトマップ


const siteMapPanel = document.getElementById("siteMapPanel");
const openSiteMap = document.getElementById("openSiteMap");
const closeSiteMap = document.getElementById("closeSiteMap");

siteMapPanel.style.display = "none";
closeSiteMap.style.display = "none";

openSiteMap.addEventListener("click", () => {
    siteMapPanel.style.display = "block";
    openSiteMap.style.display = "none";
    closeSiteMap.style.display = "inline";
});

closeSiteMap.addEventListener("click", () => {
    siteMapPanel.style.display = "none";
    openSiteMap.style.display = "inline";
    closeSiteMap.style.display = "none";
});



// 現在時刻表示
function updateClock() {
	const now = new Date();
	const format = now.toLocaleString();
	document.getElementById("clock").textContent = format;
}

updateClock();
setInterval(updateClock, 1000);

function RedirectMenu(storeId) {
	const form = document.createElement("form");
	form.method = "GET";
	form.action = `${contextPath}/MenuListServlet`;

	const input = document.createElement("input");
	input.name = "storeId";
	input.value = storeId;

	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
}

function RedirectQR(storeId) {
	const form = document.createElement("form");
	form.method = "GET";
	form.action = "/QRCodeServlet";

	const input = document.createElement("input");
	input.name = "storeId";
	input.value = storeId;

	form.appendChild(input);
	document.body.appendChild(form);
	form.submit();
}
