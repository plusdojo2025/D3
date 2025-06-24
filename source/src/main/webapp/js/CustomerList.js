document.addEventListener("DOMContentLoaded", () => {
	const forms = document.querySelectorAll("form");

	forms.forEach(form => {
		form.addEventListener("submit", function(e) {
			const submitter = e.submitter;
			if (!submitter || !submitter.name || !submitter.value) return;

			const action = submitter.value;

			// --- 更新・削除の確認 ---
			if (action.includes("update") && !action.includes("insert")) {
				if (!confirm("更新しますか？")) {
					e.preventDefault();
					return;
				}
			} else if (action.includes("delete")) {
				if (!confirm("削除しますか？")) {
					e.preventDefault();
					return;
				}
			}

			// --- キープボトル登録バリデーションと重複チェック ---
			if (action === "insert_bottle") {
				const commodity = form.querySelector("select[name='new_commodity_id']");
				const remaining = form.querySelector("input[name='new_bottle_remaining']");
				const limit = form.querySelector("input[name='new_bottle_limit']");

				if (!commodity || !remaining || !limit) return;

				if (
					!commodity.value ||
					remaining.value === "" ||
					limit.value === ""
				) {
					alert("ボトル名、残量、期限をすべて入力してください。");
					e.preventDefault();
					return;
				}

				// 重複チェック（商品名ベース）
				const newCommodityName = commodity.options[commodity.selectedIndex].text;
				let isDuplicate = false;

				form.querySelectorAll(".bottle-section p.keep").forEach(p => {
					const text = p.textContent || "";
					if (text.includes(newCommodityName)) {
						isDuplicate = true;
					}
				});

				if (isDuplicate) {
					alert("このボトルはすでに登録されています。");
					e.preventDefault();
					return;
				}
			}
		});
	});
});
