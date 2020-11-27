class DoneView{

    constructor(task) {
        this.task = task;
        this.refres = null;
    }

    delete =()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                if(this.refres !== null){
                    this.refres();
                }
            }
        });

        xhr.open('DELETE', 'http://localhost:8081/parcial2/api/task/delete/'+this.task.task);
        xhr.send();
    }
    
    downfase =()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                if(this.refres !== null){
                    this.refres();
                }
            }
        });

        xhr.open('PUT', 'http://localhost:8081/parcial2/api/task/baja/'+this.task.task);
        xhr.send();
    }

    render = () =>{
        let component = document.createElement('div');
        let task = document.createElement('p');
        let titulo = document.createElement('p');
        let fecha = document.createElement('small');
        let downBt = document.createElement('button');
        let deleteBt = document.createElement('button');

        titulo.innerHTML = 'DONE';
        downBt.innerHTML = '<<';
        downBt.className = 'downBt';
        deleteBt.innerHTML = 'x';
        deleteBt.className = 'deleteBt';
        task.innerHTML = this.task.task;
        fecha.innerHTML = this.task.fecha;
    
        component.appendChild(titulo);
        component.appendChild(fecha);
        component.appendChild(task);
        component.appendChild(downBt);
        component.appendChild(deleteBt);

        //comportamientos
        deleteBt.addEventListener('click', this.delete);
        downBt.addEventListener('click', this.downfase);

        return component;
    }
}