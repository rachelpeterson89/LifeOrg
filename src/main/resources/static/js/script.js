const date = new Date();

const renderCalendar = () => {
    date.setDate(1);

    const monthDays = document.querySelector(".days");
    
    const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate(); // last day of the current month
    
    const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();
    
    const firstDayIndex = date.getDay();
    
    const lastDayIndex = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDay();
    
    const nextMonthDays = 7 - lastDayIndex - 1;
    
    const months = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    ];
    
    document.querySelector('.date h1').innerHTML = months[date.getMonth()];
    document.querySelector('.date p').innerHTML = new Date().toDateString();
    
    let days = "";
    for(let j = firstDayIndex; j > 0; j--) {
        days += `<div class="prev-date">${prevLastDay - j + 1}</div>`;
    }
    
    for (let i = 1; i <= lastDay; i++) {
        if(i === new Date().getDate() && date.getMonth() === new Date().getMonth()) {
            days += `<div><button class="fill-div" type="button" data-toggle="modal" style="background-color: transparent;" data-target="#exampleModal" data-whatever="${date.getFullYear()}-${months[date.getMonth()]}-${i}">${i}</button></div>`;
        } else {
            days += `<div><button class="fill-div" type="button" data-toggle="modal" style="background-color: transparent;" data-target="#exampleModal" data-whatever="${date.getFullYear()}-${months[date.getMonth()]}-${i}">${i}</button></div>`;
        }
    }
    
    for (let k = 1; k <= nextMonthDays; k++) {
        days += `<div class="next-date">${k}</div>`;
        monthDays.innerHTML = days;
    }
}



document.querySelector('.prev').addEventListener('click', ()=> {
    date.setMonth(date.getMonth() - 1);
    renderCalendar();
})
document.querySelector('.next').addEventListener('click', ()=> {
    date.setMonth(date.getMonth() + 1);
    renderCalendar();
})

renderCalendar();