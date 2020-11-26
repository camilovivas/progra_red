const task = document.getElementById("task");

const bt = document.getElementById("insert");

const conte1 = document.getElementById('TODO');
const conte2 = document.getElementById('DOING');
const conte3 = document.getElementById('DONE');


const agregar =()=>{
    let taskDTO = {
        fase: 1,
        task: task.value,
        fecha:'N/A'
    };
    console.log(JSON.stringify(taskDTO));
    let xhr = new XMLHttpRequest();


    xhr.open('POST', 'http://localhost:8081/parcial2/api/task/create');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(taskDTO));

}

bt.addEventListener('click', agregar);

const getAllTaskTODO = ()=>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);

            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i];
                let view = new TodoView(taskDTO);
                conte1.appendChild(view.render);
            
            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allTODO');
    xhr.send();
}
getAllTaskTODO();

const getAllTaskDOING = ()=>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);

            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i];
                let view = new DoingView(taskDTO);
                conte2.appendChild(view.render);
            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allDOING');
    xhr.send();
}
getAllTaskDOING();
const getAllTaskDONE = ()=>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{

        if(xhr.readyState == 4){
            let json = xhr.responseText;
            let response = JSON.parse(json);

            for(let i = 0; i<response.length; i++){
                let taskDTO = response[i];
                let view = new DoneView(taskDTO);
                conte3.appendChild(view.render);
            }
        }
    });
    xhr.open('GET', 'http://localhost:8081/parcial2/api/task/allDONE');
    xhr.send();
}
getAllTaskDONE();