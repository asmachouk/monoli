import { Moment } from 'moment';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IFormation {
  id?: number;
  nom?: string;
  date?: Moment;
  nombre_participant?: number;
  employes?: IEmploye[];
}

export class Formation implements IFormation {
  constructor(
    public id?: number,
    public nom?: string,
    public date?: Moment,
    public nombre_participant?: number,
    public employes?: IEmploye[]
  ) {}
}
