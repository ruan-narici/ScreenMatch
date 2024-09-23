import { ISerie } from '../../../interfaces/ISerie';
import styles from './Description.module.scss';
import Episodes from './Episodes';
import Select from './Select';


export default function Description(serie: ISerie) {
  return (
    <div className={styles.container}>
      <h1 className={ styles.container__title }>{ serie.titulo }</h1>
      <h3 className={ styles.container__subtitle }>Média de avaliação: { serie.avaliacao }</h3>
      <p><b>Sinopse: </b>{ serie.sinopse }</p>
      <p><b>Estrelando: </b>{ serie.atores }</p>

      <Select { ...serie } />
      <Episodes />
    </div>
  );
}