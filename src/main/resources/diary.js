/*
Ohjelma ei vielä toimi full stackina. Front toimii omanaan, ei vielä keskustele jbdc:n tai tietokannan kanssa.
req.open mm ei vielä määritelty.
 */

const list = document.getElementById("list"); //se mihin koko roska tulostetaan
const side = document.getElementById("side"); //pikkuinfo -tulostus

const topic = document.getElementById("topi");
const description = document.getElementById("desc");
const link = document.getElementById("link");
const done = document.getElementById("stat");
const diary = []; //yhden topicin tiedot, 2-ulotteinen taulukko?
const allTopics = []; //kaikkien topicien aiheet, toistaiseksi vain aiheet

var req = new XMLHttpRequest();

req.onreadystatechange = function () {
    if(req.readyState === 4) {
        if(req.)
        var jason = JSON.parse();

    }
}

//ja laitetaan taulukkoon
document.getElementById("save").onclick = function() {
    const note = [topic.value, description.value, link.value, done.value];

    diary.push(note);  //toimiiko näin että jää talteen taulukko note:ja?!?!
    console.log(note);
    console.log(diary); //näyttäis toimivan, vielä vaan tulostus johonkin??
    allTopics.push(topic.value);
    side.innerHTML = "<code>" + " tallennettu"; //jotenkin vain 1 sek näkyy??
};

document.getElementById("show").onclick = function() {
    list.innerHTML = "";
    for(let i=0; i<allTopics.length; i++) {
        list.innerHTML += "<li>" + allTopics[i];
    }
};




/* testausta varten
list.innerHTML = topic;
list.innerHTML += ", " + description;
list.innerHTML += ", " + link;
list.innerHTML += ", " +  done;*/
