app.controller("order-ctrl", function($scope, $http) {
	$scope.orders = [];

	$scope.initialize = function() {
		$http.get("/rest/orders").then(resp => {
			$scope.orders = resp.data;
			$scope.orders.forEach(order => {
				order.createDate = new Date(order.createDate);
			});

			$scope.orders.sort((a, b) => b.createDate - a.createDate);
		}).catch(error => {
			alert("Lỗi tải đơn hàng!");
			console.log("Error", error);
		});
	};

	$scope.viewOrder = function(order) {
		$http.get(`/rest/orderdetails/byOrderId/${order.id}`).then(detailResp => {
			order.details = detailResp.data;
		}).catch(error => {
			console.log("Error fetching order details", error);
		});
		$scope.selectedOrder = order;
		$("#orderModal").modal("show");
	};

	$scope.deleteOrder = function(order) {
		if (confirm("Bạn muốn xóa đơn hàng này?")) {
			$http.delete(`/rest/orders/${order.id}`).then(resp => {
				var index = $scope.orders.findIndex(o => o.id == order.id);
				$scope.orders.splice(index, 1);
				alert("Xóa đơn hàng thành công!");
			}).catch(error => {
				alert("Lỗi xóa đơn hàng!");
				console.log("Error", error);
			});
		}
	};

	$scope.initialize();

	$scope.orderPager = {
		page: 0,
		size: 10,
		get orders() {
			if (this.page < 0) {
				this.last();
			}
			if (this.page >= this.count) {
				this.first();
			}
			var start = this.page * this.size;
			return $scope.orders.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil($scope.orders.length / this.size);
		},
		first() {
			this.page = 0;
		},
		last() {
			this.page = this.count - 1;
		},
		next() {
			this.page++;
		},
		prev() {
			this.page--;
		}
	};
});
