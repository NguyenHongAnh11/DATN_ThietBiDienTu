app.controller("cart-ctrl", function($scope, $http) {
	var $cart = $scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.id == id);
			if (item) {
				item.qty++;
				this.saveToLocalStorage();
				showToast('Sản phẩm đã được cập nhật trong giỏ hàng!');
			}
			else {
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
					showToast('Sản phẩm đã được cập nhật trong giỏ hàng!');

				})
			}
		},
		remove(id) { 
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() {
			this.items = []
			this.saveToLocalStorage();
		},
		amt_of(item) { 
			return item.price * item.qty;
		},
		get count() { 
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},
		get amount() { 
			return this.items
				.map(item => this.amt_of(item))
				.reduce((total, amt) => total += amt, 0);
		},get finalAmount() {
			        return this.amount - this.discountAmount; 
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() { 
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}
	}

	$cart.loadFromLocalStorage();

	$scope.order = {
		discount: 0,
		paymentMethod: null, 
		get account() {
			return { username: $auth.user.username}
		},
		createDate: new Date(),
		get orderDetails() {
			return $cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			});
		}, get orderamount() {
			return $cart.amount - discount;
		},
		purchase() {
			var order = angular.copy(this);
			$http.post(`/rest/orders/VNPAY`, order).then(resp => {
				window.location.href = resp.data.urlpay;
			}).catch(error => {
			    alert("Đặt hàng lỗi!");
			    console.log(error);
			});
			
		}
	}
	$scope.setPaymentMethod = function(method) {
	      $scope.order.paymentMethod = method;
	    };
	$scope.provinces = [];
	$scope.districts = [];
	$scope.wards = [];
	$scope.selectedProvince = null;

	$http.get(`/api/provinces`)
		.then(resp => {
			$scope.provinces = resp.data.data;
		})
		.catch(error => {
			console.error("Lỗi khi tải danh sách tỉnh thành:", error);
		});

	$scope.loadDistricts = function(provinceId) {
		$http.get(`/api/districts/${provinceId}`)
			.then(resp => {
				$scope.districts = resp.data.data;
			})
			.catch(error => {
				console.error("Lỗi khi tải danh sách quận/huyện:", error);
			});
	};
	$scope.loadwards = function(provinceId) {
		$http.get(`/api/wards/${provinceId}`)
			.then(resp => {
				$scope.wards = resp.data.data;
			})
			.catch(error => {
				console.error("Lỗi khi tải danh sách xã/phường:", error);
			});
	};
	$scope.couponValid = null; 
	$scope.discount = 0; 
	$scope.couponCode = ""; 

	$scope.applyCoupon = function(couponCode) {
	    $http.get(`/rest/coupon/check/${couponCode}`).then(function(response) {
	        if (response.data.valid) {
	            $scope.couponValid = true;
	            $scope.discount = response.data.discount; 
	            $scope.order.discount = $scope.discount; 
				$scope.order.discount = $scope.discount; 
	        } else {
	            $scope.couponValid = false;
	            $scope.discount = 0;
	            $scope.order.discount = 0; // Reset discount if invalid coupon
	        }
	    }).catch(function(error) {
	        console.error("Error applying coupon:", error);
	        $scope.couponValid = false;
	        $scope.discount = 0;
	        $scope.order.discount = 0; // Reset on error
	    });
	};


})