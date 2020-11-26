class DoneView{

    constructor(task) {
        this.task = task;
    }

    delete =()=>{

    }
    
    downfase = ()=>{
        
    }

    render = () =>{
        let component = document.createElement('div');
        let task = document.createElement('p');
        let fecha = document.createElement('small');
        let downBt = document.createElement('button');
        let deleteBt = document.createElement('button');


        downBt.innerHTML = '<<';
        downBt.className = 'downBt';
        deleteBt.innerHTML = 'x';
        deleteBt.className = 'deleteBt';
        task.innerHTML = this.task.task;
        fecha.innerHTML = this.task.fecha;
    
        component.appendChild(fecha)
        component.appendChild(task)
        
        //compportamientos
        deleteBt.addEventListener('click', this.delete);
        downBt.addEventListener('click', this.downfase);

        return component;
    }
}