import { ISerie } from '../../../interfaces/ISerie';
import styles from './Banner.module.scss';


export default function Banner({ titulo, baner }: ISerie) {
  return (
    <div className={ styles.container }>
      <img className={ styles.container__img } src={ baner } alt={ titulo } />
    </div>
  );
}