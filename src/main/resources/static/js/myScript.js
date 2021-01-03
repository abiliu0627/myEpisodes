function openNav() {
  document.getElementById("mySidepanel").style.width = "250px";
}



function closeNav() {
  document.getElementById("mySidepanel").style.width = "0";
}



function favPopup() {
  alert("Added to your favorite!");
}

// function split() {
    // alert("split function called, movie.region: " + movie.region);
    // let i;
    // for (i = 0; i < movie.region.split(",").length; i++){
    //     let newBtn = document.createElement("Button");
    //     newBtn.className = "quickFilter";
    //     newBtn.type = "button";
    //     newBtn.innerHTML = movie.region.split(",")[i]
    //     document.getElementById("tags").appendChild(newBtn)
//     // }
//     alert("split");
// }


var slideIndex = 0;
window.onload = function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}
  for (i = 0; i < dots.length; i++) {   // deactivate all dots
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  setTimeout(showSlides, 3000); // Change image every 2 seconds
}





var types = new Set(['Movie', 'TV Show', 'Animation']);
var regions = new Set(['American', 'European', 'Chinese', 'Japanese', 'Korean', 'Hindi']);
var genres = new Set(['Action', 'Comedy', 'Thriller', 'Romance', 'Musical', 'Fantasy', 'Sci-Fi', 'Drama']);
// var types = new Set(['mov', 'show', 'acg']);
// var regions = new Set(['usa', 'eur', 'chi', 'jap', 'kor', 'ind']);
// var genres = new Set(['act', 'com', 'thr', 'rom', 'mus', 'fan', 'sci', 'dra']);
var dates = new Set(['20', '15', '10', '05', '00', '90s']);
var selectedType = '';
var selectedRegion = '';
var selectedGenre = '';
var selectedDate = '';

function filter(criteria, clearAll) {     // all***: clear filters; not all***: delete prev relative filters
    var tags = document.getElementsByClassName('card'); // all cards with assigned video type
    alert("cards num is " + tags.length);
    if (!clearAll) {
        var videoType = document.getElementById(criteria); // all type filters
        var index = videoType.selectedIndex;  // type index
        var target = videoType[index].value;

        if (target === 'allVid')
            selectedType = '';
        else if (target === 'allReg')
            selectedRegion = '';
        else if (target === 'allGenr')
            selectedGenre = '';
        else if (target === 'allDates')
            selectedDate = '';

        if (types.has(target))
            selectedType = target;
        else if (regions.has(target))
            selectedRegion = target;
        else if (genres.has(target))
            selectedGenre = target;
        else if (dates.has(target))
            selectedDate = target;
    }

    else {
        // alert(document.getElementById('date').selectedIndex);
        selectedType = selectedRegion = selectedGenre = selectedDate = '';
        // document.getElementById('date').selectedIndex = 0;
        // alert(document.getElementById('date').selectedIndex);
    }

    for (var i=0; i<tags.length; i++) {
        var tag = tags[i];      // working card
        var fit = true;
        alert("tag.className: " + tag.className + ", region: " + selectedRegion + " result: " + tag.className.search(selectedRegion))
        if (tag.className.search(selectedType) === -1 || tag.className.search(selectedRegion) === -1 ||
        tag.className.search(selectedGenre) === -1 || tag.className.search(selectedDate) === -1){
            $(tag).hide();
            fit = false;
        }
        if (fit)
            $(tag).show();
    }
}



function clearFilters() {
    filter('', true);
}



function sortIt() {
  //alert('sort func');
  var list, i, switching, cards, shouldSwitch, options, index, years;
  options = document.getElementById('sort'); // all sort options
  index = options.selectedIndex;
  cards = document.getElementsByClassName("card");
  years = document.getElementsByClassName("year");
  list = document.getElementById("allCards");
  switching = true;

  while (switching) {
      //alert('inside while');
    // start by saying: no switching is done:
    switching = false;

    // Loop through all list-items:
    for (i = 0; i < (cards.length - 1); i++) {
      // start by saying there should be no switching:
      shouldSwitch = false;
      //alert('inside for loop');

      if (options[index].value === 'alph') {
          if (cards[i].innerHTML.toLowerCase() > cards[i + 1].innerHTML.toLowerCase()) {
              shouldSwitch = true;
              //alert('switch i=' + i + 'and i+1');
              break;
          }
      }

      else if (options[index].value === 'new') {
          //alert(cards[i].getElementsByClassName('year').innerHTML);
          if (years[i].innerHTML < years[i + 1].innerHTML) {
              shouldSwitch = true;
              //alert('switch i=' + i + 'and i+1');
              break;
          }
      }
        /*case 'new':
            if (cards[i].innerHTML.toLowerCase() > cards[i + 1].innerHTML.toLowerCase()) {
                shouldSwitch = true;
                break;
            }
            break;
      }*/
    }
    if (shouldSwitch) {
        //alert('switching...')
        // cards[i].parentNode
        list.insertBefore(cards[i + 1], cards[i]);
        switching = true;
    }
  }
}



