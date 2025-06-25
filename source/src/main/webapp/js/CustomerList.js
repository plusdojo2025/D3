document.addEventListener("DOMContentLoaded", () => {
	const forms = document.querySelectorAll("form");

	forms.forEach(form => {
		let clickedButton = null;

		// どのボタンが押されたか記録
		const buttons = form.querySelectorAll("button, input[type='submit']");
		buttons.forEach(button => {
			button.addEventListener("click", function () {
				clickedButton = button;
			});
		});

		form.addEventListener("submit", function (e) {
			if (!clickedButton) return;

			const action = clickedButton.value;

			// --- 顧客情報の更新・削除の確認 ---
			if (action === "顧客情報更新") {
				if (!confirm("顧客情報を更新しますか？")) {
					e.preventDefault();
					return;
				}
			} else if (action === "顧客情報削除") {
				if (!confirm("顧客情報を削除しますか？")) {
					e.preventDefault();
					return;
				}
			}

			// --- update/delete系ボタンの一括確認（talk/bottleなど） ---
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
			
			// --- 会話内容の重複チェック ---
if (action === "insert_talk") {
  const topicSelect = form.querySelector("select[name='new_topic_id']");
  if (!topicSelect) return;

  const selectedTopicId = topicSelect.value;
  let isDuplicate = false;

  // 既存の hidden フィールドから重複チェック
  form.querySelectorAll("input[type='hidden'][name^='talk_topic_id_']").forEach(hiddenInput => {
    if (hiddenInput.value === selectedTopicId) {
      isDuplicate = true;
    }
  });

  if (isDuplicate) {
    alert("このトピックはすでに登録されています。");
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