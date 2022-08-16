const openPopUp = document.getElementById('open-popup');
const closePopUp = document.getElementById('close-popup');
const popUp = document.getElementById('pop_up');

openPopUp.addEventListener('click', function (e) {
    e.preventDefault();
    popUp.classList.add('open');
})

closePopUp.addEventListener('click', () => {
    popUp.classList.remove('open');
})