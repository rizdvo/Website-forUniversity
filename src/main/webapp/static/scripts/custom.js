$(function () {
    $('.navbar-collapse a').on('click', function () {
        $(".navbar-collapse").collapse('hide');
    });

    AOS.init({
        disable: 'mobile',
        duration: 800,
        anchorPlacement: 'center-bottom'
    });

    $('.navbar a, .hero-text a').on('click', function (event) {
        const $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top - 49
        }, 1000);
        event.preventDefault();
    });
});



    

