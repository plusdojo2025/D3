package dto;

public class Store {
	
	    // === 店舗ログイン情報 ===
	    private String email;
	    private String password;

	    // === 店舗名（必要に応じて） ===
	    private String storeName;

	    // === 状態・画面遷移フラグ ===
	    private boolean deleteProvided;   // 提供済みボタン押下状態
	    private boolean goToCheckout;     // 会計画面へ遷移
	    private boolean goToRecipeList;   // レシピ一覧へ遷移
	    private boolean goToWorking;      // 業務中画面へ遷移
	    private boolean goToOffice;       // 事務作業画面へ遷移
	    private boolean logout;           // ログアウトボタン押下状態

	    // === Getter & Setter ===
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getStoreName() {
	        return storeName;
	    }

	    public void setStoreName(String storeName) {
	        this.storeName = storeName;
	    }

	    public boolean isDeleteProvided() {
	        return deleteProvided;
	    }

	    public void setDeleteProvided(boolean deleteProvided) {
	        this.deleteProvided = deleteProvided;
	    }

	    public boolean isGoToCheckout() {
	        return goToCheckout;
	    }

	    public void setGoToCheckout(boolean goToCheckout) {
	        this.goToCheckout = goToCheckout;
	    }

	    public boolean isGoToRecipeList() {
	        return goToRecipeList;
	    }

	    public void setGoToRecipeList(boolean goToRecipeList) {
	        this.goToRecipeList = goToRecipeList;
	    }

	    public boolean isGoToWorking() {
	        return goToWorking;
	    }

	    public void setGoToWorking(boolean goToWorking) {
	        this.goToWorking = goToWorking;
	    }

	    public boolean isGoToOffice() {
	        return goToOffice;
	    }

	    public void setGoToOffice(boolean goToOffice) {
	        this.goToOffice = goToOffice;
	    }

	    public boolean isLogout() {
	        return logout;
	    }

	    public void setLogout(boolean logout) {
	        this.logout = logout;
	    }
	}

	
	
	
	