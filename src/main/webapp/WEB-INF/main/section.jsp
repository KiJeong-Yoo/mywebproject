<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<div id="slide_con">
		    <div class="slidebox">
        <input type="radio" name="slide" id="slide01" checked>
        <input type="radio" name="slide" id="slide02">
        <input type="radio" name="slide" id="slide03">
        <input type="radio" name="slide" id="slide04">
        <ul class="slidelist">
            <li class="slideitem">
                <div>
                    <label for="slide04" class="left"></label>
                    <label for="slide02" class="right"></label>
                    <a><img src="http://placehold.it/1000x300"></a>
                </div>
            </li>
            <li class="slideitem">
                <div>
                    <label for="slide01" class="left"></label>
                    <label for="slide03" class="right"></label>
                    <a><img src="http://placehold.it/1000x300"></a>
                </div>
            </li>
            <li class="slideitem">
                <div>
                    <label for="slide02" class="left"></label>
                    <label for="slide04" class="right"></label>
                    <a><img src="http://placehold.it/1000x300"></a>
                </div>
            </li>
            <li class="slideitem">
                <div>
                    <label for="slide03" class="left"></label>
                    <label for="slide01" class="right"></label>
                    <a><img src="http://placehold.it/1000x300"></a>
                </div>
            </li>
        </ul>
    </div>
	</div>
	
	<div class="weather-wrapper">
            <div class="seoul weather-card">
                <div class="City weathercity seoul">서울</div>
                <span class="CurrIcon weathericon seoul"></span>
                <span class="CurrTemp weathertemp seoul"></span>
            </div>
            
            <div class="incheon weather-card">
                <div class="City2 weathercity incheon">인천</div>
                <span class="CurrIcon2 weathericon incheon"></span>
                <span class="CurrTemp2 weathertemp incheon"></span>
            </div> 
             
            <div class="uijeongbu weather-card">
                <div class="City6 weathercity Uijeongbu-si">의정부</div>
                <span class="CurrIcon6 weathericon Uijeongbu-si"></span>
                <span class="CurrTemp6 weathertemp Uijeongbu-si"></span>
            </div>
            
            <div class="guri weather-card">
                <div class="City21 weathercity guri">구리</div>
                <span class="CurrIcon21 weathericon guri"></span>
                <span class="CurrTemp21 weathertemp guri"></span>
            </div>
            
            <div class="paju weather-card">
                <div class="City19 weathercity paju">파주</div>
                <span class="CurrIcon19 weathericon paju"></span>
                <span class="CurrTemp19 weathertemp paju"></span>
            </div>
            
            <div class="suwon weather-card">
                <div class="City16 weathercity suwon">수원</div>
                <span class="CurrIcon16 weathericon suwon"></span>
                <span class="CurrTemp16 weathertemp suwon"></span>
            </div>
            
            <div class="yongin weather-card">
                <div class="City20 weathercity yongin">용인</div>
                <span class="CurrIcon20 weathericon yongin"></span>
                <span class="CurrTemp20 weathertemp yongin"></span>
            </div>
            
            <div class="chuncheon weather-card">
                <div class="City14 weathercity Chuncheon">춘천</div>
                <span class="CurrIcon14 weathericon Chuncheon"></span>
                <span class="CurrTemp14 weathertemp Chuncheon"></span>
            </div>
            
            <div class="sokcho weather-card">
                <div class="City13 weathercity Sokcho">속초</div>
                <span class="CurrIcon13 weathericon Sokcho"></span>
                <span class="CurrTemp13 weathertemp Sokcho"></span>
            </div>
            
            <div class="gangneung weather-card">
                <div class="City15 weathercity Gangneung">강릉</div>
                <span class="CurrIcon15 weathericon Gangneung"></span>
                <span class="CurrTemp15 weathertemp Gangneung"></span>
            </div>   
            
            <div class="cheongju weather-card">
                <div class="City11 weathercity Cheongju-si">청주</div>
                <span class="CurrIcon11 weathericon Cheongju-si"></span>
                <span class="CurrTemp11 weathertemp Cheongju-si"></span>
			</div>
			
            <div class="cheonan weather-card">
                <div class="City12 weathercity Cheonan">천안</div>
                <span class="CurrIcon12 weathericon Cheonan"></span>
                <span class="CurrTemp12 weathertemp  Cheonan"></span>
            </div>

            
            <div class="daejeon weather-card">
                <div class="City5 weathercity daejeon">대전</div>
                <span class="CurrIcon5 weathericon daejeon"></span>
                <span class="CurrTemp5 weathertemp daejeon"></span>
            </div>

            <div class="daegu weather-card">
                <div class="City7 weathercity daegu">대구</div>
                <span class="CurrIcon7 weathericon daegu"></span>
                <span class="CurrTemp7 weathertemp daegu"></span>
            </div>

            <div class="ulsan weather-card">
                <div class="City8 weathercity Ulsan">울산</div>
                <span class="CurrIcon8 weathericon Ulsan"></span>
                <span class="CurrTemp8 weathertemp Ulsan"></span>
            </div>
            
            <div class="ulsan weather-card">
                <div class="City8 weathercity Ulsan">울산</div>
                <span class="CurrIcon8 weathericon Ulsan"></span>
                <span class="CurrTemp8 weathertemp Ulsan"></span>
            </div>


            <div class="pohang weather-card">
                <div class="City17 weathercity pohang">포항</div>
                <span class="CurrIcon17 weathericon pohang"></span>
                <span class="CurrTemp17 weathertemp pohang"></span>
            </div>  
            
            <div class="jeonju weather-card">
                <div class="City18 weathercity jeonju">전주</div>
                <span class="CurrIcon18 weathericon jeonju"></span>
                <span class="CurrTemp18 weathertemp jeonju"></span>
            </div>
            
            <div class="gwangju weather-card">
                <div class="City9 weathercity Gwangju">광주</div>
                <span class="CurrIcon9 weathericon Gwangju"></span>
                <span class="CurrTemp9 weathertemp Gwangju"></span>
            </div>
            
            <div class="mokpo weather-card">
                <div class="City10 weathercity Mokpo">목포</div>
                <span class="CurrIcon10 weathericon Mokpo"></span>
                <span class="CurrTemp10 weathertemp Mokpo"></span>
            </div>

            <div class="jeju weather-card">
                <div class="City3 weathercity jeju">제주</div>
                <span class="CurrIcon3 weathericon jeju"></span>
                <span class="CurrTemp3 weathertemp jeju"></span>
            </div>
    </div>
    
    <!-- 게시판 -->
   <div id="board_con">
		<jsp:include page="/WEB-INF/debateboard/sec_debate.jsp" />
		<%-- <jsp:include page="/WEB-INF/photoboard/sec_photo.jsp" /> --%>
    </div>
    
    <!-- 뉴스 -->
    <div id="news_con">
    	<jsp:include page="/WEB-INF/news/mainrandom.jsp" />
    </div>         
</section>
<script>
//날씨
$(document).ready(function() {
    let weatherIcon = {
        '01' : 'fas fa-sun',
        '02' : 'fas fa-cloud-sun',
        '03' : 'fas fa-cloud',
        '04' : 'fas fa-cloud-meatball',
        '09' : 'fas fa-cloud-sun-rain',
        '10' : 'fas fa-cloud-showers-heavy',
        '11' : 'fas fa-poo-storm',
        '13' : 'fas fa-snowflake',
        '50' : 'fas fa-smog'
    };

    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp').prepend($Temp);
        }
    })
    
    $.ajax({
	    url: 'http://api.openweathermap.org/data/2.5/weather?q=Suwon&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
	    dataType:'json',
	    type:'GET',
	    success: function(data) {
	        var $Icon = (data.weather[0].icon).substr(0,2);
	        var $Temp = Math.floor(data.main.temp)+'°';
	
	        $('.CurrIcon16').append('<i class="'+weatherIcon[$Icon]+'"></i>');
	        $('.CurrTemp16').prepend($Temp);
	    }
	})
	
	$.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Incheon&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon2').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp2').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Jeju City&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon3').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp3').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Busan&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon4').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp4').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Daejeon&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon5').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp5').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Uijeongbu-si&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon6').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp6').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Daegu&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon7').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp7').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Ulsan&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon8').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp8').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Gwangju&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon9').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp9').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Mokpo&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon10').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp10').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Cheongju-si&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon11').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp11').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Cheonan&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon12').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp12').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Sokcho&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon13').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp13').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Chuncheon&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon14').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp14').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Gangneung&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon15').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp15').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Pohang&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon17').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp17').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Jeonju&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon18').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp18').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Paju&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon19').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp19').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Yongin&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon20').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp20').prepend($Temp);
        }
    })
    
    $.ajax({
        url: 'http://api.openweathermap.org/data/2.5/weather?q=Guri-si&appid=f8bdc421944f166c29f793a4ea5c5231&units=metric',
        dataType:'json',
        type:'GET',
        success: function(data) {
            var $Icon = (data.weather[0].icon).substr(0,2);
            var $Temp = Math.floor(data.main.temp)+'°';

            $('.CurrIcon21').append('<i class="'+weatherIcon[$Icon]+'"></i>');
            $('.CurrTemp21').prepend($Temp);
        }
    })
});
</script>  