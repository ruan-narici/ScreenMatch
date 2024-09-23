import { ISerie } from '../../interfaces/ISerie';
import Banner from './Banner';
import Description from './Description';
import styles from './Resume.module.scss';


export default function Resume(serie: ISerie) {
  return (
    <section className={ styles.container }>
      <Description {...serie} />
      <Banner { ...serie } />
    </section>
  );
}