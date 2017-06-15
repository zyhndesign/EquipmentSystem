$(document).ready(function() {
	var open = 0;
	$("#logoIcon").bind("click", function() {
		console.log('click');
		var leftResult;
		if (open) {
			leftResult = "+=200px";
		} else {
			leftResult = "-=200px";
		}

		var rightResult;
		if (open) {
			rightResult = "-=200px";
		} else {
			rightResult = "+=200px";
		}

		if (open) {
			$(".zyMain").animate({
				left : leftResult

			}, 1000, function() {
				if (open) {
					open = 0;
				} else {
					open = 1;
				}
			})

			$(".zyMain").animate({
				width : rightResult
			}, 1000, function() {

			})
			$(".zyMain").dequeue();

		} else {
			$(".zyMain").animate({
				width : rightResult
			}, 1000, function() {

			})

			$(".zyMain").animate({
				left : leftResult

			}, 1000, function() {
				if (open) {
					open = 0;
				} else {
					open = 1;
				}
			})
			$(".zyMain").dequeue();
		}

	});
});