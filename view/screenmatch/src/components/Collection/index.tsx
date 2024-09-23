import { ICollection } from '../../interfaces/ICollection';
import Card from './Card';
import styles from './Collection.module.scss';


export default function Collection({ title, series } : ICollection) {
  return (
    <section className={ styles.container }>
      <h1 className={ styles.container__title }>{ title }</h1>
      <div className={ styles.container__cards }>
        { 
          series && series.map(serie => 
            <Card key={ serie.id } {...serie} />) 
        }
      </div>
    </section>
  );
}