class DoingView{

constructor(task){
 this.task = task;
}

render = () =>{
    let component = document.createElement('div');
    let task = document.createElement('p');
    let fecha = document.createElement('small');
    
    task.innerHTML = this.task.task;
    fecha.innerHTML = this.task.fecha;

    component.appendChild(fecha)
    component.appendChild(task)

    return component;
}

}