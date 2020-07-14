$(function() {

	let _window = $(window);
	_window.on('scroll', function () {
		if(_window.scrollTop() > 25/*px*/){
			$('header').addClass('fixed');
		} else {
			$('header').removeClass('fixed');
		}
	});
	_window.trigger('scroll');

	let windowWidth = $(window).width();
	let headerHight = 30;
	$('a[href^="#"]').click(function() {
		let speed = 800;
		let href= $(this).attr("href");
		let target = $(href == "#" || href == "" ? 'html' : href);
		let position = target.offset().top-headerHight;
		$('body,html').animate({scrollTop:position}, speed, 'swing');
		return false;
	});

	let $pagetop = $('#page_top');
	$pagetop.hide();
	$(window).scroll(function () {
		if ($(this).scrollTop() > 100) {
			$pagetop.fadeIn();
		} else {
			$pagetop.fadeOut();
		}
	});
	$pagetop.on('click', function () {
		$('body,html').animate({
			scrollTop: 0
		}, 300);
		return false;
	});

	$('.target-subj').on('click', function () {
		let selectedSubject = $(this).data().subject;
		window.location = '/question?subject=' + selectedSubject;
	});


});