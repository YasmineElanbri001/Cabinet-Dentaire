function showDelete(hide,show,tr){
    document.getElementById(hide).style.display="none";
    document.getElementById(show).classList.replace("d-none","d-block");
}

function hideDelete(show,hide,tr){
    document.getElementById(show).style.display="inline";
    document.getElementById(hide).classList.replace("d-block","d-none");
}
