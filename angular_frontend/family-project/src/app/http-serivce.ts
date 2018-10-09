import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Family, Father, Child } from "./app.component";

@Injectable()
export class HttpService {

    constructor(public http: HttpClient){}

    searchChild(paramsMap){
        let params = new HttpParams();
        Object.keys(paramsMap).forEach(param => {
            params = params.append(param.toString(), paramsMap[param]);
        })
        return this.http.get<Number[]>("http://localhost:8080/search/child", {params: params});
    }

    readFamilyByChildId(id:Number){
        let params = new HttpParams().set('childId', id+"");
        return this.http.get<Family>("http://localhost:8080/read/family", {params: params})
    }

    readFamily(id:Number){
        return this.http.get<Family>("http://localhost:8080/read/family/"+id);
    }

    createFather(father:Father, fam_id: Number){
        let params = new HttpParams().set('familyid', fam_id+"");
        return this.http.post<Number>("http://localhost:8080/add/father",father,{params: params});
    }

    createFamily(){
        return this.http.get<Number>("http://localhost:8080/create/family");
    }

    createChild(child: Child, fam_id: Number){
        let params = new HttpParams().set('familyid', fam_id+"");
        return this.http.post<Number>("http://localhost:8080/add/child",child,{params: params});
    }
}